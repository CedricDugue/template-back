package br.com.trackertemplateback.error;

/**
 * Classe utilizada para atribuir os novos valores do erro quando um determinado
 * registro nao e encontrado.<br/>
 * Valores atribuidos na classe RestExceptionHandler.<br/>
 * Herda classse ErrorDetail com o cabecalho padrao das mensagens de erro.
 *
 * @author Cedric Christian on 30/04/2021
 */
public class ResourceNotFoundDetails extends ErrorDetails {

    public static final class Builder {

        private int status;
        private String error;
        private String message;
        private String detailMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder detailMessage(String detailMessage) {
            this.detailMessage = detailMessage;
            return this;
        }

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();

            resourceNotFoundDetails.setStatus(status);
            resourceNotFoundDetails.setError(error);
            resourceNotFoundDetails.setMessage(message);
            resourceNotFoundDetails.setDetailMessage(detailMessage);

            return resourceNotFoundDetails;
        }
    }
}
