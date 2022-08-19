package com.machado.fabiano.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.machado.fabiano.spring.data.service.BuscaDinamicaFuncionario;
import com.machado.fabiano.spring.data.service.BuscaService;
import com.machado.fabiano.spring.data.service.CrudCargoService;
import com.machado.fabiano.spring.data.service.CrudFuncionarioService;
import com.machado.fabiano.spring.data.service.CrudUnidadeDeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService;
	private final BuscaService buscaService;
	private final BuscaDinamicaFuncionario buscaDinamicaFuncionario;

	public SpringDataApplication(
			CrudCargoService cargoService, 
			CrudFuncionarioService funcionarioService, 
			CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService, 
			BuscaService buscaService, 
			BuscaDinamicaFuncionario buscaDinamicaFuncionario) {
				this.cargoService = cargoService;
				this.funcionarioService = funcionarioService;
				this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
				this.buscaService = buscaService;
				this.buscaDinamicaFuncionario = buscaDinamicaFuncionario;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		system = true;
		
		Scanner scanner = new Scanner(System.in);
		
		while (system) {
			System.out.println("Escolha a ação a ser executada:");
			System.out.println("0 - Sair");
			System.out.println("1 - Gerenciar cargos");
			System.out.println("2 - Gerenciar funcionários");
			System.out.println("3 - Gerenciar unidades de trabalho");
			System.out.println("4 - Realizar buscas padrão");
			System.out.println("5 - Realizar buscas dinâmicas");
			
			Integer opcao = scanner.nextInt();
			
			switch (opcao) {
			case 1:
				cargoService.menu(scanner);
				break;
			case 2:
				funcionarioService.menu(scanner);
				break;
			case 3:
				unidadeDeTrabalhoService.menu(scanner);
				break;
			case 4:
				buscaService.menu(scanner);
				break;
			case 5:
				buscaDinamicaFuncionario.menu(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
}
//		Cargo cargo = new Cargo("Programador");
//		repository.save(cargo);