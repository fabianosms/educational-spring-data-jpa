package com.machado.fabiano.spring.data.orm;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

	public Cargo() {
	}

	public Cargo(String descricao) {
		this.descricao = descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.descricao);
	}
}
