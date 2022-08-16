package br.com.trackertemplateback.error.handler;

import br.com.trackertemplateback.error.ErrorDetails;
import br.com.trackertemplateback.error.ResourceNotFoundDetails;
import br.com.trackertemplateback.error.ResourceNotFoundException;
import br.com.trackertemplateback.error.ValidationErrorDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe para tratamento de erros, excecoes.<br/>
 * Principal funcao dessa classe e manter a ordem de padronizacao em que a
 * mensagem de erro e exibida.
 *
 * @author Cedric Christian on 30/04/2021
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Erro enviado atraves do objeto Controller quando nao encontrado um
     * determinado registro.
     *
     * @param ex Classe com valores atribuidos quando nao encontrado um
     *           determinado registro.
     * @return Erro padronizado.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("Resource not found")
                .message(ex.getMessage())
                .detailMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Erro enviado atraves do objeto Controller quando ocorre algum erro na
     * validacao dos campos da entidade.
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Field validation error")
                .message("Field validation error")
                .detailMessage(ex.getClass().getName())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();

        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Tratamento dos erros internos ocorridos no sistema.
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             @Nullable Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .status(status.value())
                .error("Internal Exception")
                .message(ex.getMessage())
                .detailMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, headers, status);
    }

    /**
     * Tratamento de conflitos como erro de constraint.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .detailMessage(ex.getClass().getName() + " - " + ex.getMostSpecificCause().getMessage())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    /**
     * Tratamento dos erros genericos ocorridos no sistema.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {

        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Exception")
                .message(ex.getMessage())
                .detailMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Tratamento do erro de acesso negado para permissao ao endpoint.<br>
     *
     * @sample Token invalido
     */
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {

        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .status(HttpStatus.FORBIDDEN.value())
                .error("Access Denied")
                .message(ex.getMessage())
                .detailMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }
}
