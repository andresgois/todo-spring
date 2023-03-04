package br.com.main.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.main.dto.TodoRequestDTO;
import br.com.main.dto.TodoResponseDTO;
import br.com.main.model.Todo;
import br.com.main.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("http://localhost:4200")
@Api("API para criação de tarefas")
public class TodoController {

	@Autowired
	private TodoService todoService;
	

    @GetMapping
    @ApiOperation("Endpoint para consulta de todas as tarefas")
    public ResponseEntity<List<TodoResponseDTO>> getAll(){
        List<TodoResponseDTO> todoAll = todoService.all();
        return ResponseEntity.ok().body(todoAll);
    }
    
	@PostMapping
	@ApiOperation("Endpoint para criação de tarefa")
	public ResponseEntity<TodoResponseDTO> save(@RequestBody TodoRequestDTO todoDto) {
	    TodoResponseDTO todo = todoService.create(todoDto);
		//return ResponseEntity.ok().body(todo);
	    //return new ResponseEntity<>(TodoResponseDTO.dataTransferObject(todo), HttpStatus.CREATED);
	    Long id = todo.getId();
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	    return ResponseEntity.created(uri).body(todo);
	}
	
	@GetMapping("{id}")
    public ResponseEntity<TodoResponseDTO> getById(@PathVariable Long id) throws Exception {
	    TodoResponseDTO todo = todoService.findTodo(id);
        return ResponseEntity.ok().body(todo);
    }
	
	@DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
	    todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
	
	@PatchMapping("{id}/done")
    public ResponseEntity<TodoResponseDTO> markAsDone(@PathVariable Long id) {
        TodoResponseDTO todo = todoService.todoDone(id);
        return ResponseEntity.ok().body(todo);
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
