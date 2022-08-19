package com.machado.fabiano.spring.data.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "funcionarios")
@SuppressWarnings("unused")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private BigDecimal salario;
	private LocalDate dataDeContratacao;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", 
	joinColumns = {@JoinColumn(name = "fk_funcionario")}, 
	inverseJoinColumns = {@JoinColumn(name = "fk_unidade")})
	private List<UnidadeDeTrabalho> unidadeDeTrabalho;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public void setDataDeContratacao(LocalDate dataDeContratacao) {
		this.dataDeContratacao = dataDeContratacao;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void setUnidadeDeTrabalho(List<UnidadeDeTrabalho> unidadeDeTrabalho) {
		this.unidadeDeTrabalho = unidadeDeTrabalho;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s), %s", this.nome, this.cpf, this.cargo);
	}
	
}
