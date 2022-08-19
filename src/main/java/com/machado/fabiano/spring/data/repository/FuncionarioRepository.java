package com.machado.fabiano.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.machado.fabiano.spring.data.orm.Funcionario;
import com.machado.fabiano.spring.data.orm.FuncionarioDto;
import com.machado.fabiano.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends 
		PagingAndSortingRepository<Funcionario, Integer>,
		JpaSpecificationExecutor<Funcionario> {

	List<Funcionario> findByNome(String nome);
	
//	List<Funcionario> findByNome(String nome, Pageable pageable); // com Paging
	
	@Query("SELECT f FROM Funcionario f JOIN FETCH f.cargo WHERE f.nome = :nome")
	List<Funcionario> buscarFuncionarioComCargo(String nome);
	
	List<Funcionario> findByCargoDescricao(String descricao);
	
	@Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
	List<Funcionario> buscarFuncionarioPelaDescricaoDoCargo(String descricao);
	
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataDeContratacao
		(String nome, BigDecimal salario, LocalDate data);
	
	@Query("SELECT f FROM Funcionario f "
			+ "WHERE f.nome = :nome AND f.salario >= :salario AND f.dataDeContratacao = :data")
	List<Funcionario> buscarFuncionarioPorNomeESalarioMaiorQueEDataDeContratacao
		(String nome, BigDecimal salario, LocalDate data);
//		(@Param("nome") String nome, @Param("salario") BigDecimal salario, @Param("data") LocalDate data);
	@Query("SELECT f FROM Funcionario f WHERE f.nome= :nome AND f.salario >= :salario AND f.dataDeContratacao = :data")
	
	List<Funcionario> findByUnidadeDeTrabalho_Nome(String nome);
	
	@Query("SELECT f FROM Funcionario f JOIN f.unidadeDeTrabalho u WHERE u.nome = :nome")
	List<Funcionario> buscarFuncionarioPorNomeDeUnidadeDeTrabalho(String nome);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_de_contratacao >= :data",
			nativeQuery = true)
	List<Funcionario> buscarFuncionarioPorDataDeContratacaoMaior(LocalDate data);

	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
//	@Query("SELECT f.id AS id, f.nome AS nome, f.salario AS salario FROM Funcionario f")
	List<FuncionarioProjecao> visualizarFuncionariosESalarios();
	
    @Query(value = "SELECT new com.machado.fabiano.spring.data.orm.FuncionarioDto(f.id, f.nome, f.salario) FROM Funcionario f")
    List<FuncionarioDto> visualizarFuncionariosESalariosDto();
	
	List<Funcionario> findByNomeLike(String nome);
//	String nome = "%maria%";
	
	List<Funcionario> findByNomeEndingWith(String nome);
	
	List<Funcionario> findByNomeStartingWith(String nome);
	
	List<Funcionario> findByNomeIsNull();
	
	List<Funcionario> findByNomeIsNotNull();
	
	List<Funcionario> findByNomeOrderByNomeAsc(String nome);
	
	List<Funcionario> findByNomeContainingIgnoreCase(String nome);
}
