package br.com.main.dto;

import br.com.main.model.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDTO {
    
    private String description;
    
    private Boolean done;
    
    public Todo dataTransferObject() {
        return new Todo(description, done);
    }
}
