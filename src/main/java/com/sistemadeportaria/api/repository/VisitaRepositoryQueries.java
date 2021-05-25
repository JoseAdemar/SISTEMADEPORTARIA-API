package com.sistemadeportaria.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.sistemadeportaria.api.model.Visita;

public interface VisitaRepositoryQueries {

	List<Visita> find(String setor, LocalDateTime dataDaVisita);
}
