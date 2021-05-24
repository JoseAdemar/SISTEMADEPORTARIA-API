package com.sistemadeportaria.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.model.Visitante;

public interface VisitaRepositoryQueries {

	List<Visita> find(LocalDateTime dataDaVisita, String setor, Visitante visitante);
}
