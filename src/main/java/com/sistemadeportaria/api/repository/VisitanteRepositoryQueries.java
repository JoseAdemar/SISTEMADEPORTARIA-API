package com.sistemadeportaria.api.repository;

import java.util.List;

import com.sistemadeportaria.api.model.Visitante;

public interface VisitanteRepositoryQueries {

	List<Visitante> find(String nome, String cpf, String telefone);
}
