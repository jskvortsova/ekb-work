package ekb.work.domain;


import ekb.work.enums.ResponseStatus;

/**
 * Created by juliaskvortsova on 9/4/16.
 */
public class ResponseDto {
    private Object response;
    private String message;
    private ResponseStatus status;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
