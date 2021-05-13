package com.sistemadeportaria.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistemadeportaria.api.model.Visitante;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {

	//Optional<Visitante> findByCpf(String cpf);
	
	@Query("from Visitante where cpf like %:cpf% and nome.nome like %:nome%")
	List<Visitante> consultarPorCpf(String cpf, @Param("nome") String nome);
}
