package br.com.main.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.main.dto.TodoRequestDTO;
import br.com.main.dto.TodoResponseDTO;
import br.com.main.model.Todo;
import br.com.main.service.TodoService;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@PostMapping
	public ResponseEntity<TodoResponseDTO> save(@RequestBody TodoRequestDTO todoDto) {
	    Todo todo = todoService.create(todoDto.dataTransferObject());
		//return ResponseEntity.ok().body(todo);
	    //return new ResponseEntity<>(TodoResponseDTO.dataTransferObject(todo), HttpStatus.CREATED);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId()).toUri();
	    return ResponseEntity.created(uri).body(TodoResponseDTO.dataTransferObject(todo));
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
	    List<Todo> todoAll = todoService.all();
		return ResponseEntity.ok().body(todoAll);
	}
	
	/*@GetMapping("{id}")
	public Todo getById(@PathVariable Long id) {
		return repository
				.findById(id)
				.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
				);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PatchMapping("{id}/done")
	public Todo markAsDone(@PathVariable Long id) {
		return repository.findById(id).map(
				todo -> {
					todo.setDone(true);
					todo.setDoneDate(LocalDateTime.now());
					repository.save(todo);
					return todo;
				}
		).orElse(null);
	}*/
	
}
