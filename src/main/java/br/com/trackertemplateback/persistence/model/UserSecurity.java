package br.com.trackertemplateback.persistence.model;

import br.com.trackertemplateback.persistence.model.generic.AbstractEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Entidade para configurar a autenticacao do token do usuario e senha, ou seja,
 * autentica as credenciais do usuario e senha da aplicacao externa.<br/>
 * Utilizada na classe br.com.biblioapi.config.SecurityConfig
 *
 * @author Cedric Christian on 30/04/2021
 */
@Entity
public class UserSecurity extends AbstractEntity {

    @NotBlank(message = "Username is required")
    @Column(unique = true, nullable = false, length = 30)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(nullable = false, length = 100)
    private String password;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private boolean admin;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @NotBlank(message = "Expiration date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(nullable = true)
    private Date expirationDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
