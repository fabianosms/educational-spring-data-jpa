package com.machado.fabiano.spring.data.orm;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unidades_de_trabalho")
public class UnidadeDeTrabalho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;
	
	@ManyToMany(mappedBy = "unidadeDeTrabalho", fetch = FetchType.EAGER)
	private List<Funcionario> funcionarios;
	
	public UnidadeDeTrabalho() {
	}
	
	public UnidadeDeTrabalho(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("%d - %s - %s", this.id, this.nome, this.endereco);
	}
	
}
