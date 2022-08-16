package br.com.trackertemplateback.error;

import java.util.Date;

/**
 * Define o cabecalho padrao das mensagens de erro.
 *
 * @author Cedric Christian on 30/04/2021
 */
public class ErrorDetails {

    private Date timestamp = new Date();
    private int status;
    private String error;
    private String message;
    private String detailMessage;

    protected ErrorDetails() {
    }

    @Override
    public String toString() {
        return "ErrorDetails{\" +\n" +
                "                \"timestamp=\" + timestamp +\n" +
                "                \", status=\" + status +\n" +
                "                \", error='\" + error + '\\'' +\n" +
                "                \", message='" + message + '\'' +
                ", detailMessage='" + detailMessage + '\'' +
                '}';
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

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

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();

            errorDetails.setStatus(status);
            errorDetails.setError(error);
            errorDetails.setMessage(message);
            errorDetails.setDetailMessage(detailMessage);

            return errorDetails;
        }
    }
}
