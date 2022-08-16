package br.com.trackertemplateback.config.securityaccess.authentication;

import br.com.trackertemplateback.persistence.model.UserSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static br.com.trackertemplateback.config.securityaccess.SecurityConstantEnum.*;

/**
 * Classe de autenticacao com a finalidade de identificacao de acesso (quem esta
 * acessando).<br/>
 * Dois tipos de autenticacao:<br/>
 * Autenticacao - Identificar quem esta acessando.<br/>
 * Autorizacao - Permissoes de acesso. Autorizar as requisicoes.<br/>
 * Seguranca com token JWT
 *
 * @author Cedric Christian on 30/04/2021
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Metodo de autenticacao.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserSecurity userSecurity = new ObjectMapper().readValue(request.getInputStream(), UserSecurity.class);

            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userSecurity.getUsername(), userSecurity.getPassword()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gera token se autenticado com sucesso.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        // Data de validade do token
        ZonedDateTime expTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(Long.parseLong(EXPIRATION_TIME.getDescription()), ChronoUnit.MILLIS);
        if (EXPIRATION_TIME.getDescription().equals("0"))
            expTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plusYears(99);

        String username = ((User) authResult.getPrincipal()).getUsername();

        // Gera token
        String token = Jwts
                .builder()
                .setSubject(username)
                .setExpiration(Date.from(expTimeUTC.toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET.getDescription())
                .compact();

        // Retorna o token no header e no body
        String bearerToken = TOKEN_PREFIX.getDescription() + token;
        response.getWriter().write(bearerToken); // Adiciona token no body
        response.addHeader(HEADER_STRING.getDescription(), bearerToken); // Adiciona token no header
    }
}
