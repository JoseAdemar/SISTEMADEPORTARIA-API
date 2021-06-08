package com.sistemadeportaria.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sistemadeportaria.api.exception.VisitaNaoEncontradaException;
import com.sistemadeportaria.api.exception.VisitanteNaoEncontradoException;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.repository.VisitanteRepository;

@Service
public class VisitanteService {

	@Autowired
	private VisitanteRepository visitanteRepository;

	// Metodo para cadastrar um visitante
	public Visitante cadastrarVisitante(Visitante visitante) {

		Visitante salvarVisitante = visitanteRepository.save(visitante);

		return salvarVisitante;
	}

	// Metodo para listar todos os visitantes
	public List<Visitante> ListarTodosVisitantes() {

		return visitanteRepository.findAll();

	}

	
	// Metodo para buscar por CPF
	public Visitante visitanteConsultaDinamica(String nome, String cpf, String telefone) {

		List<Visitante> buscarCpf = visitanteRepository.find(nome, cpf, telefone);

		if (buscarCpf != null) {

			for (Visitante visitante : buscarCpf) {
				return visitante;
			}
		}

		throw new VisitanteNaoEncontradoException(String.format("Não foi encontrado os dados informados"));

	}

	// Metodo para atualizar um visitante
	public void atualizarCadastroVisitante(Visitante visitante, Long id) {

		Visitante atualizar = visitanteRepository.findById(id)
				.orElseThrow(() -> new VisitanteNaoEncontradoException(id));

		if (atualizar != null) {

			BeanUtils.copyProperties(visitante, atualizar, "id");

			visitanteRepository.save(atualizar);
		}

	}

	// Metodo para deletarUmVisitante

	public void deletarVisitantePorId(Long id) {

		try {

			visitanteRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new VisitaNaoEncontradaException(id);

		}
	}

}
