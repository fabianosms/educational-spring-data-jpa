package com.machado.fabiano.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.machado.fabiano.spring.data.orm.Funcionario;
import com.machado.fabiano.spring.data.orm.FuncionarioDto;
import com.machado.fabiano.spring.data.orm.FuncionarioProjecao;
import com.machado.fabiano.spring.data.repository.FuncionarioRepository;

@Service
public class BuscaService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public BuscaService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void menu(Scanner scanner) {
		
		Boolean system = true;
		
		while (system) {
			System.out.println("Que tipo de buscar deseja realizar?");
			System.out.println("0 - Retornar ao menu anterior");
			System.out.println("1 - Funcionários pelo nome");
			System.out.println("2 - Funcionários por unidade de trabalho");
			System.out.println("3 - Funcionários por nome, salário e data de contratação");
			System.out.println("4 - Funcionários por data de contratação");
			System.out.println("5 - Exibir funcionários e salários pro projeção");
			System.out.println("6 - Exibir funcionários e salários por DTO");
			
			Integer opcao = scanner.nextInt();
			
			switch (opcao) {
			case 1:
//				buscarFuncionarioPeloNome(scanner);
				buscarFuncionarioComCargoPeloNome(scanner);
				break;
			case 2:
				buscarFuncionarioPorUnidadeDeTrabalho(scanner);
				break;
			case 3:
				buscarFuncionarioPorNomeESalarioMaiorQueEDataDeContratacao(scanner);
				break;
			case 4:
				buscarFuncionarioPorDataDeContratacaoMaior(scanner);
				break;
			case 5:
				visualizarFuncionariosESalarios();
				break;
			case 6:
				visualizarFuncionariosESalariosDto();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
//	private void buscarFuncionarioPeloNome(Scanner scanner) {
//		System.out.println("Digite o nome a ser pesquisado");
//		String nome = scanner.nextLine();
//		nome += scanner.nextLine();
//		List<Funcionario> funcionariosPorNome = funcionarioRepository.findByNome(nome);
//		funcionariosPorNome.forEach(System.out::println);
//	}
	
	private void buscarFuncionarioComCargoPeloNome(Scanner scanner) {
		System.out.println("Digite o nome a ser pesquisado com cargo");
		String nome = scanner.nextLine();
		nome += scanner.nextLine();
		List<Funcionario> funcionariosPorNomeComCargo = funcionarioRepository.buscarFuncionarioComCargo(nome);
		funcionariosPorNomeComCargo.forEach(System.out::println);
	}
	
	private void buscarFuncionarioPorUnidadeDeTrabalho(Scanner scanner) {
		System.out.println("Digite a unidade de trabalho a ser pesquisada");
		String nome = scanner.nextLine();
		nome += scanner.nextLine();
		List<Funcionario> funcionariosPorUnidadeDeTrabalho = funcionarioRepository.findByUnidadeDeTrabalho_Nome(nome);
		funcionariosPorUnidadeDeTrabalho.forEach(System.out::println);
	}
	
	private void buscarFuncionarioPorNomeESalarioMaiorQueEDataDeContratacao(Scanner scanner) {
		System.out.println("Digite o nome a ser pesquisado");
		String nome = scanner.nextLine();
		nome += scanner.nextLine();
		
		System.out.println("Digite o salario a ser pesquisado");
		BigDecimal salario = scanner.nextBigDecimal();
		
		System.out.println("Digite a data a ser pesquisada");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> funcionariosPorNomeSalarioData = funcionarioRepository
				.buscarFuncionarioPorNomeESalarioMaiorQueEDataDeContratacao
				(nome, salario, localDate);
		funcionariosPorNomeSalarioData.forEach(System.out::println);
	}
	
	private void buscarFuncionarioPorDataDeContratacaoMaior(Scanner scanner) {
		System.out.println("Digite a data a ser pesquisada");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> funcionariosPorDataDeContratacao = funcionarioRepository
				.buscarFuncionarioPorDataDeContratacaoMaior(localDate);
		funcionariosPorDataDeContratacao.forEach(System.out::println);
	}
	
	private void visualizarFuncionariosESalarios() {
		List<FuncionarioProjecao> funcionariosESalarios = 
				funcionarioRepository.visualizarFuncionariosESalarios();
		funcionariosESalarios.forEach(f -> System.out.println("Id " + f.getId() 
			+ ", Nome: " + f.getNome() + ", Salário: " + f.getSalario()));
	}
	
	private void visualizarFuncionariosESalariosDto() {
		List<FuncionarioDto> funcionariosESalariosDto = 
				funcionarioRepository.visualizarFuncionariosESalariosDto();
		funcionariosESalariosDto.forEach(f -> System.out.println("Id " + f.getId() 
			+ ", Nome: " + f.getNome() + ", Salário: " + f.getSalario()));
	}
}
