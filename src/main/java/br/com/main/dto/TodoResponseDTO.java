package br.com.main.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {
 
    private Long id;
    private String description;
    private Boolean done;
    private LocalDateTime createdDate;
    
    public Long getId() {
        return this.id;
    }
    
    
    
    /*
     * public static TodoResponseDTO dataTransferObject(Todo todo) { return new
     * TodoResponseDTO( todo.getId(), todo.getDescription(), todo.getDone(),
     * todo.getCreatedDate()); }
     */
}
