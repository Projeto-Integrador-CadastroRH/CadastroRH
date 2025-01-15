package com.generation.cadastrorh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.generation.cadastrorh.model.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Long> {

	public List<Funcionarios> findAllByNomeColaboradorContainingIgnoreCase(@Param("nomeColaborador")String nomeColaborador);

}
