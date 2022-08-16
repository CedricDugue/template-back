package br.com.trackertemplateback.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.context.*;

import java.io.IOException;

/**
 * Classe para tratamento de erros, excecoes, ocorridos nas requisicoes dos
 * templates (br.com.biblioapi.templates).<br/>
 * Retorna os detalhes padronizados dos erros gerados na classe
 * RestExceptionHandler.
 *
 * @author Cedric Christian on 30/04/2021
 */
@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error(IOUtils.toString(response.getBody(), "UTF_8"));
    }
}
