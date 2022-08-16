package br.com.trackertemplateback.config.securityaccess.authorization;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.trackertemplateback.config.securityaccess.SecurityConstantEnum.*;

/**
 * Classe de autorizacao com a finalidade de controlar as permissoes de acesso
 * (autorizar as requisicoes).<br/>
 * Dois tipos de autenticacao:<br/>
 * Autenticacao - identificar quem esta acessando.<br/>
 * Autorizacao - Permissoes de acesso. Autorizar as requisicoes.<br/>
 * Seguranca com token JWT
 *
 * @author Cedric Christian on 30/04/2021
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final CustomUserDetailService customUserDetailService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CustomUserDetailService customUserDetailService) {
        super(authenticationManager);
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING.getDescription());

        if (header == null || !header.startsWith(TOKEN_PREFIX.getDescription())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING.getDescription());

        if (token == null) return null;

        String username = Jwts.parser().setSigningKey(SECRET.getDescription())
                .parseClaimsJws(token.replace(TOKEN_PREFIX.getDescription(), ""))
                .getBody()
                .getSubject();

        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        return username != null ? new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
    }
}
