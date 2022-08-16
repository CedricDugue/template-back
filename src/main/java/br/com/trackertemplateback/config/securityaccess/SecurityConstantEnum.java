package br.com.trackertemplateback.config.securityaccess;

import static br.com.trackertemplateback.util.properties.GetProperties.FilePropertiesEnum.APPLICATION;
import static br.com.trackertemplateback.util.properties.GetProperties.propertiesFile;

/**
 * Constantes com valores padroes utilizados nas classes de seguranca:<br/>
 * JWTAuthenticationFilter, JWTAuthorizationFilter e SecurityConfig
 *
 * @author Cedric Christian on 30/04/2021
 */
public enum SecurityConstantEnum {
    // Chave para gerar token
    SECRET("ms@8iE#ja%$8Y2Y&9lwr!1qA-b6c4V++X7.xka5j&ye%vV2g%f8#daAe9$9Edq@@fR8&av-5gZ++8i-p"),
    TOKEN_PREFIX("Bearer "),
    HEADER_STRING("Authorization"),
    SIGN_UP_URL("/swagger-ui.html"),
    EXPIRATION_TIME(propertiesFile(APPLICATION).getProperty("expiration.time.token")),
    PROFILE_ACTIVE(propertiesFile(APPLICATION).getProperty("spring.profiles.active"));

    private String description;

    SecurityConstantEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
