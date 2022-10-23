package br.com.main.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException(String msg) {
        super(msg);
    }
    
}
