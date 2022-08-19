package com.machado.fabiano.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.machado.fabiano.spring.data.orm.UnidadeDeTrabalho;

@Repository
public interface UnidadeDeTrabalhoRepository extends CrudRepository<UnidadeDeTrabalho, Integer> {

	public UnidadeDeTrabalho findByNome(String nome);
	
}
