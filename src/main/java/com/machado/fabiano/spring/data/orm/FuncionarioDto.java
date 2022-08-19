package com.machado.fabiano.spring.data.orm;

import java.math.BigDecimal;

public class FuncionarioDto {

	private Integer id;
    private String nome;
    private BigDecimal salario;
    
    public FuncionarioDto(Integer id, String nome, BigDecimal salario) {
		this.id = id;
		this.nome = nome;
		this.salario = salario;
	}
    
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
}
