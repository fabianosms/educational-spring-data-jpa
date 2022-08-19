package com.machado.fabiano.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.machado.fabiano.spring.data.orm.Cargo;
import com.machado.fabiano.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void menu(Scanner scanner) {
		
		Boolean system = true;
		
		while (system) {
			System.out.println("O que deseja realizar?");
			System.out.println("0 - Retornar ao menu anterior");
			System.out.println("1 - Adicionar cargo");
			System.out.println("2 - Renomear cargo");
			System.out.println("3 - Visualizar cargos");
			System.out.println("4 - Remover cargo");
			
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
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Informe a descrição do cargo");
		String descricao = scanner.nextLine();
		descricao += scanner.nextLine();
		
		Cargo novoCargo = new Cargo(descricao);
		cargoRepository.save(novoCargo);
		System.out.println("Cargo adicionado com sucesso");
	}
		
//		Cargo cargo = new Cargo(descricao);
//		cargoRepository.save(cargo);
//		System.out.println("Cargo adicionado com sucesso");
		
		
//		Cargo cargo = cargoRepository.findByDescricao(descricao);
//		
//		if (cargoRepository.existsById(cargo.getId())) {
//			System.out.println("Cargo já existente");
//			system = false;
//		} else {
////			novoCargo.setDescricao(descricao);
//			Cargo novoCargo = new Cargo(descricao);
//			cargoRepository.save(novoCargo);
//			System.out.println("Cargo adicionado com sucesso");
//		}
		
//		Boolean existe = cargoRepository.existsById(cargo.getId());
//		System.out.println(existe);
	
//	private void jaExiste(String descricao) {
//		
//		Iterable<Cargo> cargos = cargoRepository.findAll();
//		
//		for (Cargo cargo : cargos) {
//			if (cargo.getDescricao() == descricao) {
//				throw new RuntimeException("Cargo já existente");
//			} else {
//				Cargo novoCargo = new Cargo(descricao);
//				cargoRepository.save(novoCargo);
//				System.out.println("Cargo adicionado com sucesso");
//			}
//	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Qual cargo você deseja atualizar?");
		String descricao = scanner.nextLine();
        descricao += scanner.nextLine();
        Cargo cargo = cargoRepository.findByDescricao(descricao);
        
        System.out.println("Nova descrição para o cargo " + cargo);
        String novaDescricao = scanner.nextLine();
        cargo.setDescricao(novaDescricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo " + cargo + " atualizado com sucesso");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Qual cargo você deseja remover?");
		
		String descricao = scanner.nextLine();
        descricao += scanner.nextLine();
        Cargo cargo = cargoRepository.findByDescricao(descricao);
        
        Integer id = cargo.getId();
        cargoRepository.deleteById(id);
        
        System.out.println("Cargo " + cargo + " removido com sucesso");
	}
}
