package br.com.main.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import br.com.main.dto.TodoRequestDTO;
import br.com.main.dto.TodoResponseDTO;
import br.com.main.model.Todo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TodoMapper {
    
    private final ModelMapper mapper;
    
    public Todo toTodoRequest(TodoRequestDTO request) {
        return mapper.map(request, Todo.class);
    }
    
    public TodoResponseDTO toTodoResponse(Todo response) {
        return mapper.map(response, TodoResponseDTO.class);
    }

    public List<TodoResponseDTO> toTodoResponseList(List<Todo> todos) {
        return todos.stream()
                .map(this::toTodoResponse)
                .collect(Collectors.toList());
    }
}
