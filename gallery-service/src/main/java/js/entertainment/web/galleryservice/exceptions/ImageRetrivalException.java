package js.entertainment.web.galleryservice.exceptions;

import org.springframework.http.HttpStatus;

public class ImageRetrivalException extends RuntimeException {
    private final HttpStatus status;

    public ImageRetrivalException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
