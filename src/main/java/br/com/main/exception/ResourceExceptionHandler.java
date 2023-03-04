package br.com.main.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.main.dto.StandardErrorDTO;
import br.com.main.service.exception.EntityNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardErrorDTO> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        StandardErrorDTO err = new StandardErrorDTO();
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setIp(request.getRemoteAddr());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}