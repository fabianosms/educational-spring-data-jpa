package com.machado.fabiano.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.machado.fabiano.spring.data.orm.UnidadeDeTrabalho;
import com.machado.fabiano.spring.data.repository.UnidadeDeTrabalhoRepository;

@Service
public class CrudUnidadeDeTrabalhoService {

	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	
	public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
	}
	
	public void menu(Scanner scanner) {
		
		Boolean system = true;
		
		while (system) {
			System.out.println("O que deseja realizar?");
			System.out.println("0 - Retornar ao menu anterior");
			System.out.println("1 - Adicionar unidade de trabalho");
			System.out.println("2 - Renomear unidade de trabalho");
			System.out.println("3 - Visualizar unidades de trabalho");
			System.out.println("4 - Remover unidade de trabalho");
			
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
		System.out.println("Informe o nome da unidade");
		String nome = scanner.nextLine();
		
		System.out.println("Informe o endereço da unidade");
		String endereco = scanner.nextLine();
		
		UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho(nome, endereco);
		
		unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
		System.out.println("Unidade de trabalho adicionada com sucesso");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Qual unidade de trabalho você deseja atualizar?");
		String nome = scanner.nextLine();
        nome += scanner.nextLine();
        UnidadeDeTrabalho unidadeDeTrabalho = unidadeDeTrabalhoRepository.findByNome(nome);
        
        System.out.println("Novo nome para a unidade de trabalho " + unidadeDeTrabalho);
        String novoNome = scanner.nextLine();
        
        System.out.println("Novo endereço para a unidade de trabalho " + unidadeDeTrabalho);
        String novoEndereco = scanner.nextLine();
        
        unidadeDeTrabalho.setNome(novoNome);
        unidadeDeTrabalho.setEndereco(novoEndereco);
        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Unidade de trabalho " + unidadeDeTrabalho + " atualizada com sucesso");
	}
	
	private void visualizar() {
		Iterable<UnidadeDeTrabalho> unidadesDeTrabalho = unidadeDeTrabalhoRepository.findAll();
		unidadesDeTrabalho.forEach(unidadeDeTrabalho -> System.out.println(unidadeDeTrabalho));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Qual unidade de trabalho você deseja remover?");
		
		String nome = scanner.nextLine();
        nome += scanner.nextLine();
        UnidadeDeTrabalho unidadeDeTrabalho = unidadeDeTrabalhoRepository.findByNome(nome);
        
        Integer id = unidadeDeTrabalho.getId();
        unidadeDeTrabalhoRepository.deleteById(id);
        
        System.out.println("Unidade de trabalho " + unidadeDeTrabalho + " removida com sucesso");
	}
	
}
