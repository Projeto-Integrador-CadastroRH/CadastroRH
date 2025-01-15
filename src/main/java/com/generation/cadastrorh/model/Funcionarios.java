package com.generation.cadastrorh.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_funcionarios")
public class Funcionarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENTO
	private Long id;
	
	@NotBlank(message = "O atributo Nome do Colaborador é obrigatório")
	@Size(min = 10, max = 150, message = "O atributo Nome do Colaborador pode ter no mínimo é entre 10 até 150 caracteres")
	private String nomeColaborador;
	
	@Column(nullable = false)
	@NotNull(message = "A data de admissão é obrigatória.")
	private LocalDate dateAdmissao;
	
	@NotNull(message = "O atributo Salário é obrigatório")
	@Column(precision = 10, scale = 2)
	private BigDecimal salario;

	@NotBlank(message = "O atributo Cargo é obrigatório")
	@Size(min = 10, max = 150, message = "O atributo Cargo pode ter no mínimo é entre 10 até 150 caracteres")
	private String cargo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}
	
	public LocalDate getDateAdmissao() {
		return dateAdmissao;
	}

	public void setDateAdmissao(LocalDate dateAdmissao) {
		this.dateAdmissao = dateAdmissao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
}
