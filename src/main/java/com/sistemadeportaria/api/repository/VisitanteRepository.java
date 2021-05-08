package com.sistemadeportaria.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadeportaria.api.model.Visitante;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {

	Optional<Visitante> findByCpf(String cpf);
}
