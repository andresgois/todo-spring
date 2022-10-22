package br.com.main.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.main.dto.StandardErrorDTO;
import br.com.main.dto.TodoRequestDTO;
import br.com.main.dto.TodoResponseDTO;
import br.com.main.model.Todo;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    
    public static final TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);
    
    //@Mapping(source = "status", target = "status")
    public Todo toTodoDto(TodoRequestDTO todoRequestDTO);
    
    public Todo toTodoDto(TodoResponseDTO todoResponseDTO);
}
