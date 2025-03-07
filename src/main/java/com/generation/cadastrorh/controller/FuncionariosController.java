package com.generation.cadastrorh.controller;
import java.util.List;
import java.util.Optional;

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

	
	@PostMapping // cadastrar
	public ResponseEntity<Funcionarios> post(@Valid @RequestBody Funcionarios funcionarios){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(funcionariosRepository.save(funcionarios));
	}
	
	@PutMapping // atualizar
	public ResponseEntity<Funcionarios> put(@Valid @RequestBody Funcionarios funcionarios){
		return funcionariosRepository.findById(funcionarios.getId())
				.map //if
				(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(funcionariosRepository.save(funcionarios)))
				
				.orElse
					(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
											
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable Long id) {
		Optional <Funcionarios> funcionarios = funcionariosRepository.findById(id);
		
		if(funcionarios.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		funcionariosRepository.deleteById(id); 
	}

}
