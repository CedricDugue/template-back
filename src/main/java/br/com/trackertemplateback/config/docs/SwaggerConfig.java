package br.com.trackertemplateback.config.docs;

import br.com.trackertemplateback.util.properties.GetProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Properties;

import static br.com.trackertemplateback.util.properties.GetProperties.propertiesFile;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configuracao do objeto que faz a documentacao do sistema - swagger.<br/>
 * Exemplo acesso JSON: http://localhost:8080/v2/api-docs<br/>
 * Exemplo acesso HTML: http://localhost:8080/swagger-ui.html
 *
 * @author Cedric Christian on 30/04/2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.trackertemplateback.controller.v1"))
                .paths(regex("/v1.*"))
                .build()
                .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                        .name("Authorization")
                        .description("Bearer token")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .build()))
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Properties prop = propertiesFile(GetProperties.FilePropertiesEnum.SWAGGERMESSAGE);

        return new ApiInfoBuilder()
                .title(prop.getProperty("title"))
                .description(prop.getProperty("description"))
                .version(prop.getProperty("version"))
                .license(prop.getProperty("license"))
                .licenseUrl(prop.getProperty("license.url"))
                .contact(new Contact(
                        prop.getProperty("contact.name"),
                        prop.getProperty("contact.url"),
                        prop.getProperty("contact.email")))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
