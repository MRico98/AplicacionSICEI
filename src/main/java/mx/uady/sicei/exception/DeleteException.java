package mx.uady.sicei.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DeleteException extends RuntimeException {

    public DeleteException() {
        super("No se pudo eliminar la entidad");
    }

    public DeleteException(String mensaje) {
        super(mensaje);
    }
    
}
