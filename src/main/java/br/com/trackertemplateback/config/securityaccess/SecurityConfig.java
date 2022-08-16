package br.com.trackertemplateback.config.securityaccess;

import br.com.trackertemplateback.config.securityaccess.authentication.JWTAuthenticationFilter;
import br.com.trackertemplateback.config.securityaccess.authorization.CustomUserDetailService;
import br.com.trackertemplateback.config.securityaccess.authorization.JWTAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import static br.com.trackertemplateback.config.securityaccess.SecurityConstantEnum.PROFILE_ACTIVE;

/**
 * Configura a autorizacao de acesso, ou seja, as permissoes de requisicao de acesso externo.
 *
 * @author Cedric Christian on 30/04/2021
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Busca as permissoes de autorizacao de acesso no banco de dados - model UserSecurity
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Autenticacao com Token.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .authorizeRequests()
                .antMatchers("/*/trackertemplateback/**").hasRole("USER")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));

        if (PROFILE_ACTIVE.getDescription().equals("dev"))
            http.csrf().disable();
    }
}
