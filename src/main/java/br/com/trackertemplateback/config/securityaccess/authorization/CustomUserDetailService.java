package br.com.trackertemplateback.config.securityaccess.authorization;

import br.com.trackertemplateback.persistence.model.UserSecurity;
import br.com.trackertemplateback.persistence.repository.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Gerencia as regras de permissao de acesso do usuario externo.<br/>
 * Busca as permissoes de autorizacao de acesso no banco de dados - model UserSecurity
 *
 * @author Cedric Christian on 30/04/2021
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailService implements UserDetailsService {

    private final UserSecurityRepository userSecurityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity userSecurity = Optional.ofNullable(userSecurityRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        List<GrantedAuthority> authorityList = userSecurity.isAdmin()
                ? AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN")
                : AuthorityUtils.createAuthorityList("ROLE_USER");

        boolean accountNonExpired = true;

        if (Optional.ofNullable(userSecurity.getExpirationDate()).isPresent()) {
            accountNonExpired = new Date().before(userSecurity.getExpirationDate());

            if (!accountNonExpired)
                throw new UsernameNotFoundException("Account expired.");
        }

        return new User(userSecurity.getUsername(), userSecurity.getPassword(), userSecurity.isActive(),
                accountNonExpired, accountNonExpired, userSecurity.isActive(), authorityList);
    }
}
