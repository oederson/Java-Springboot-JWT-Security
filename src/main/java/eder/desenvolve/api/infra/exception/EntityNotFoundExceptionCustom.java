package eder.desenvolve.api.infra.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundExceptionCustom extends RuntimeException {
    public EntityNotFoundExceptionCustom(String message) {
        super(message);
    }
}