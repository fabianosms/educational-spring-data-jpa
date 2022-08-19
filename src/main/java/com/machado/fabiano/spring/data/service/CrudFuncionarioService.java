package com.machado.fabiano.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.machado.fabiano.spring.data.orm.Cargo;
import com.machado.fabiano.spring.data.orm.Funcionario;
import com.machado.fabiano.spring.data.orm.UnidadeDeTrabalho;
import com.machado.fabiano.spring.data.repository.CargoRepository;
import com.machado.fabiano.spring.data.repository.FuncionarioRepository;
import com.machado.fabiano.spring.data.repository.UnidadeDeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, 
			CargoRepository cargoRepository, 
			UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
	}
	
	public void menu(Scanner scanner) {
		
		Boolean system = true;
		
		while (system) {
			System.out.println("O que deseja realizar?");
			System.out.println("0 - Retornar ao menu anterior");
			System.out.println("1 - Adicionar funcionário");
			System.out.println("2 - Renomear funcionário");
			System.out.println("3 - Visualizar todos os funcionários");
			System.out.println("4 - Visualizar funcionários por página");
			System.out.println("5 - Remover funcionários");
			
			Integer opcao = scanner.nextInt();
			
			switch (opcao) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				visualizarPorPagina(scanner);
				break;
			case 5:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Informe o nome do funcionário");
		String nome = scanner.nextLine();
		nome += scanner.nextLine();
		
		System.out.println("Informe o cpf do funcionário");
		String cpf = scanner.nextLine();
		
		System.out.println("Informe o salário do funcionário");
		BigDecimal salario = scanner.nextBigDecimal();
		
		System.out.println("Informe a data de contratação do funcionário");
		String dataDeContratacao = scanner.next();
		
		System.out.println("Informe o cargo do funcionário");
		String cargoDescricao = scanner.nextLine();
		cargoDescricao += scanner.nextLine();
		
		System.out.println("Informe o(s) id(s) da(s) unidade(s) de trabalho do funcionário");
		List<UnidadeDeTrabalho> unidades = unidade(scanner);
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataDeContratacao(LocalDate.parse(dataDeContratacao, formatter));
        Cargo cargo = cargoRepository.findByDescricao(cargoDescricao);
        funcionario.setCargo(cargo);
        funcionario.setUnidadeDeTrabalho(unidades);
        
        funcionarioRepository.save(funcionario);

        System.out.println("Funcionário " + funcionario + " adicionado com sucesso");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Informe o id do funcionário que você deseja atualizar");
		Integer id = scanner.nextInt();
		
		System.out.println("Digite o nome");
		String nome = scanner.nextLine();
		nome += scanner.nextLine();

        System.out.println("Digite o cpf");
        String cpf = scanner.nextLine();
		cpf += scanner.nextLine();

        System.out.println("Digite o salário");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.println("Digite a data de contração");
        String dataDeContratacao = scanner.next();

        System.out.println("Digite o cargo");
        String cargoDescricao = scanner.nextLine();
		cargoDescricao += scanner.nextLine();
		
		Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataDeContratacao(LocalDate.parse(dataDeContratacao, formatter));
        Cargo cargo = cargoRepository.findByDescricao(cargoDescricao);
        funcionario.setCargo(cargo);
        
        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário " + funcionario + " atualizado com sucesso");
	}
	
	private void visualizar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void visualizarPorPagina(Scanner scanner) {
		System.out.println("Qual página você deseja visualizar?");
		Integer pagina = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome"));
//		Pageable pageable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome", "salario", "cpf"));
		
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página " + funcionarios.getNumber() + " de " + (funcionarios.getTotalPages() - 1));
		System.out.println("Total de funcionários: " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o id do funcionário você deseja remover");
		Integer id = scanner.nextInt();
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		funcionarioRepository.deleteById(id);
		System.out.println("Funcionário " + funcionario + " removido com sucesso");
	}
	
	private List<UnidadeDeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeDeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o nome da unidade (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeDeTrabalho> unidade = unidadeDeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return unidades;
    }
}

//@Service
//public class ClienteService {
//
//    private ClienteRepository repository;
//
//    public ClienteService(ClienteRepository repository) {
//        this.repository = repository;
//    }
//
//    public void cadastrar(Cliente novo) {
//        boolean jaCadastrado = repository.existsByCpf(novo.getCpf());
//        if(jaCadastrado) {
//            throw new RegraDeNegocioException("Cliente com o mesmo CPF já está cadastrado!");
//        }
//        // outras regras de negocio...
//
//        repository.save(novo);
//    }
//}
