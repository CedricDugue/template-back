package br.com.trackertemplateback.config.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Define metodos de retorno de chamada para personalizar a configuracao baseada em Java para o Spring MVC ativada via @EnableWebMvc.<br/>
 * As classes de configuracao anotadas com @EnableWebMvc podem implementar essa interface para ser chamada de volta e personalizar a configuracao padrao.
 *
 * @author Cedric Christian on 30/04/2021
 */
@Configuration
class TrackerSystemAdapter implements WebMvcConfigurer {

    /**
     * Define configuracao padrao do Pageable utilizado no objeto controller.
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver phmaResolver = new PageableHandlerMethodArgumentResolver();
        phmaResolver.setFallbackPageable(PageRequest.of(0, 10));
        resolvers.add(phmaResolver);
    }
}
