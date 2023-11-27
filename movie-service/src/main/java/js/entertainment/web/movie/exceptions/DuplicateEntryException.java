package js.entertainment.web.movie.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateEntryException extends RuntimeException {
    private final HttpStatus status;

    public DuplicateEntryException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
