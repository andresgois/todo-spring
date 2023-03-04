package br.com.main.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardErrorDTO implements Serializable{
   
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private String ip;
    
}
