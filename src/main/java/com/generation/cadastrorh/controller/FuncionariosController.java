package com.generation.cadastrorh.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.cadastrorh.model.Funcionarios;
import com.generation.cadastrorh.repository.FuncionariosRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")   //Define qual endpoint vai ser tratado por essa classe 
@CrossOrigin(origins = "*", allowedHeaders = "*") //Libera o acesso a qualquer front
public class FuncionariosController {
	
	@Autowired
	private FuncionariosRepository funcionariosRepository;
	
	@GetMapping
	public ResponseEntity<List<Funcionarios>> getAll(){  //Indica que esse metodo e chamado em Verbos/Metodos
		return ResponseEntity.ok(funcionariosRepository.findAll()); //SELECT * FROM tb_funcionarios
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Funcionarios> getByID (@PathVariable Long id){  
		return funcionariosRepository.findById(id)		//Buscar por ID na tabela 
				.map(reposta -> ResponseEntity.ok(reposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}    
	
	@GetMapping ("/nome/{nomeColaborador}")
	public ResponseEntity<List<Funcionarios>> getBynomeColaborador (@PathVariable String nomeColaborador){  
		return ResponseEntity.ok(funcionariosRepository.findAllByNomeColaboradorContainingIgnoreCase(nomeColaborador));
	
	}    


}
