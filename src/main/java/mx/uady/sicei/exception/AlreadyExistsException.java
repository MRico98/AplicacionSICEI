package mx.uady.sicei.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
        super("La entidad no pudo ser creada.");
    }

    public AlreadyExistsException(String mensaje) {
        super(mensaje);
    }
    
}
