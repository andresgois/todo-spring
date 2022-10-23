package br.com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.main.dto.TodoRequestDTO;
import br.com.main.dto.TodoResponseDTO;
import br.com.main.mapper.TodoMapper;
import br.com.main.model.Todo;
import br.com.main.repository.TodoRepository;
import br.com.main.service.exception.EntityNotFoundException;

@Service
public class TodoService {

    private final TodoMapper mapper;
    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository, TodoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TodoResponseDTO create(TodoRequestDTO todoDto) {
        Todo todo = mapper.toTodoRequest(todoDto);
        Todo todoSave = repository.save(todo);
        return mapper.toTodoResponse(todoSave);
        // return repository.save(mapper.toTodoRequest(todoDto));
    }

    public List<TodoResponseDTO> all() {
        List<Todo> todos = repository.findAll();
        List<TodoResponseDTO> responses = mapper.toTodoResponseList(todos);
        return responses;
        // return repository.findAll();
    }

    public TodoResponseDTO findTodo(Long id) throws Exception {

        Todo response = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not foind: " + id));

        return mapper.toTodoResponse(response);

    }

    public void deleteTodo(Long id) {
       Todo todo = repository.findById(id).orElseThrow(
               () -> new EntityNotFoundException("Id not foind: " + id));
       repository.deleteById(id);      
    }

    public TodoResponseDTO todoDone(Long id) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not foind: " + id));
        todo.setDone(true);
        repository.save(todo);
        return mapper.toTodoResponse(todo);
    }
   
}
