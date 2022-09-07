package br.com.main.dto;

import java.time.LocalDateTime;

import br.com.main.model.Todo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TodoResponseDTO {
 
    private Long id;
    private String description;
    private Boolean done;
    private LocalDateTime createdDate;
    
    public static TodoResponseDTO dataTransferObject(Todo todo) {
        return new TodoResponseDTO(
                todo.getId(),
                todo.getDescription(),
                todo.getDone(),
                todo.getCreatedDate());
    }
}
