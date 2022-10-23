package br.com.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDTO {
    
    private String description;
    
    private Boolean done;
    
    /*
     * public Todo dataTransferObject() { return new Todo(description, done); }
     */
}
