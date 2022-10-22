package br.com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.main.config.MapperConfig;
import br.com.main.dto.TodoRequestDTO;
import br.com.main.mapper.TodoMapper;
import br.com.main.model.Todo;
import br.com.main.repository.TodoRepository;
import br.com.main.service.exception.EntityNotFoundException;

@Service
public class TodoService {
    
    private MapperConfig mapper;
    private TodoRepository repository;
    
    @Autowired
    public TodoService(TodoRepository repository,MapperConfig mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    public Todo create(Todo todo) {
        return repository.save(todo);
    }
    
    public List<Todo> all() {
        return repository.findAll();
    }

    public Todo findTodo(Long id) throws Exception {
        
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not foind: "+id));
        
    }
}
