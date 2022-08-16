package br.com.trackertemplateback.persistence.repository;

import br.com.trackertemplateback.persistence.model.UserSecurity;
import org.springframework.data.repository.Repository;

/**
 * @author Cedric Christian on 30/04/2021
 */
public interface UserSecurityRepository extends Repository<UserSecurity, Long> {

    UserSecurity findByUsername(String username);
}
