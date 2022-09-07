package br.com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.main.model.Todo;
import br.com.main.repository.TodoRepository;

@Service
public class TodoService {
    
    private TodoRepository repository;
    
    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }
    
    public Todo create(Todo todo) {
        return repository.save(todo);
    }
    
    public List<Todo> all() {
        return repository.findAll();
    }
}
