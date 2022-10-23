package br.com.main.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.main.dto.TodoRequestDTO;
import br.com.main.model.Todo;
import br.com.main.repository.TodoRepository;
import br.com.main.service.exception.EntityNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TodoControllerTest {

    @Autowired
    ObjectMapper mapper;
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    TodoRepository repository;
    
    @BeforeEach
    void up() {
        Todo t = new Todo();
        t.setDescription("Ir ao supermercado");
        t.setDone(false);
        t.setCreatedDate(LocalDateTime.now());
        t.setDoneDate(null);
        repository.save(t);
    }
    
    @AfterEach
    void down() {
        repository.deleteAll();
    }
    
    @Test
    @DisplayName("Lista todos os todo")
    void todoList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    @DisplayName("Salvar um todo")
    void todoSave() throws Exception {
        TodoRequestDTO t = TodoRequestDTO.builder()
                    .description("Comprar almoço")
                    .done(false)
                    .build();
        String pRequest = mapper.writeValueAsString(t);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(pRequest))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    @DisplayName("Todo não encontrado")
    void todoNotFound() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/10"))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(
                result -> assertThat(result.getResolvedException() instanceof EntityNotFoundException)
             )
            .andDo(MockMvcResultHandlers.print());
    }
    
    
}
