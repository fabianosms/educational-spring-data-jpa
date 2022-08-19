package com.machado.fabiano.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.machado.fabiano.spring.data.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

	public Cargo findByDescricao(String descricao);
	
}
