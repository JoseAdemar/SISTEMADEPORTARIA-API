package com.sistemadeportaria.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadeportaria.api.model.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {

	Optional<Visita> findByDataDaVisita(LocalDateTime dataDaVisita);
	
	

}
