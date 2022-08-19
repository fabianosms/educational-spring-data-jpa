package com.machado.fabiano.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.machado.fabiano.spring.data.orm.Funcionario;
import com.machado.fabiano.spring.data.repository.FuncionarioRepository;
import com.machado.fabiano.spring.data.specification.FuncionarioSpecification;

@Service
public class BuscaDinamicaFuncionario {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;
	
	public BuscaDinamicaFuncionario(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void menu(Scanner scanner) {
		System.out.println("Digite o nome (0 para ignorar)");
		String nome = scanner.nextLine();
		nome = scanner.nextLine();
		
		if (nome.equalsIgnoreCase("0")) {
			nome = null;
		}
		
		System.out.println("Digite o cpf (0 para ignorar)");
		String cpf = scanner.nextLine();
		
		if (cpf.equalsIgnoreCase("0")) {
			cpf = null;
		}
		
		System.out.println("Digite o salário (0 para ignorar)");
		BigDecimal salario = scanner.nextBigDecimal();
		
		if (salario.equals(BigDecimal.ZERO)) { 
			salario = null;
		}
		
		System.out.println("Digite a data de contratação (0 para ignorar)");
		String data = scanner.next();
		
		LocalDate dataDeContratacao;
		
		if (data.equalsIgnoreCase("0")) {
			dataDeContratacao = null;
		} else {
			dataDeContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification
				.where(
					FuncionarioSpecification.nome(nome))
					.or(FuncionarioSpecification.cpf(cpf))
					.or(FuncionarioSpecification.salario(salario))
					.or(FuncionarioSpecification.dataDeContratacao(dataDeContratacao))
				);
		funcionarios.forEach(System.out::println);
	}
}
