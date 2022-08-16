package br.com.trackertemplateback.error;

/**
 * Classe utilizada para atribuir os novos valores do erro em validacoes de
 * campos da entidade.<br/>
 * Valores atribuidos na classe RestExceptionHandler.<br/>
 * Herda classse ErrorDetail com o cabecalho padrao das mensagens de erro.
 *
 * @author Cedric Christian on 30/04/2021
 */
public class ValidationErrorDetails extends ErrorDetails {

    private String field;
    private String fieldMessage;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public static final class Builder {

        private int status;
        private String error;
        private String message;
        private String detailMessage;
        private String field;
        private String fieldMessage;

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

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();

            validationErrorDetails.setStatus(status);
            validationErrorDetails.setError(error);
            validationErrorDetails.setMessage(message);
            validationErrorDetails.setDetailMessage(detailMessage);
            validationErrorDetails.setField(field);
            validationErrorDetails.setFieldMessage(fieldMessage);

            return validationErrorDetails;
        }
    }
}
