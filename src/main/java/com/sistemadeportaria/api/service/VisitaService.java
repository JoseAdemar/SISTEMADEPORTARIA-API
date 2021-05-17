package com.sistemadeportaria.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadeportaria.api.execoes.EntidadeNaoEncontradaException;
import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.repository.VisitaRepository;
import com.sistemadeportaria.api.repository.VisitanteRepository;

@Service
public class VisitaService {

	@Autowired
	private VisitaRepository visitaRepository;

	@Autowired
	private VisitanteRepository visitanteRepository;

	// Metodo para listar todas as visitas
	public List<Visita> listarVisitas() {

		return visitaRepository.findAll();
	}

	// Metodo para cadastrar uma visita
	public Visita cadastrarVisita(Visita visita) {

		Optional<Visitante> visitante = visitanteRepository.findById(visita.getVisitante().getId());

		Visita visitas = visitaRepository.save(visita);

		if (visitante.isPresent() & visitas != null) {

			return visitaRepository.save(visita);
		}

		throw new EntidadeNaoEncontradaException("Não foi possível realizar o cadastro");
	}

	public Visita pesquisarVisitaPorDataECpf(LocalDateTime visitaData) {

		Optional<Visita> pesquisaData = visitaRepository.findByDataDaVisita(visitaData);

		if (pesquisaData.isPresent()) {

			 pesquisaData.get().getDataDaVisita();
		}
		return pesquisaData.get();

	}
}
