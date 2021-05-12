package com.sistemadeportaria.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sistemadeportaria.api.execoes.EntidadeNaoEncontradaException;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.repository.VisitanteRepository;

@Service
public class VisitanteService {

	@Autowired
	private VisitanteRepository visitanteRepository;

	// Metodo para cadastrar um visitante
	public Visitante cadastrarVisitante(Visitante visitante) {

		Visitante salvarVisitante = visitanteRepository.save(visitante);

		if (salvarVisitante != null) {

			return salvarVisitante;

		}

		throw new EntidadeNaoEncontradaException(String.format("Verifique os campos obrigatórios"));

	}

	// Metodo para listar todos os visitantes
	public List<Visitante> ListarTodosVisitantes() {

		return visitanteRepository.findAll();

	}

	// Metodo para buscar por CPF

	public Visitante buscarPorCpf(String cpf) {

		Optional<Visitante> buscarCpf = visitanteRepository.findByCpf(cpf);

		if (buscarCpf.isPresent()) {

			return buscarCpf.get();

		}

		throw new EntidadeNaoEncontradaException("Visitante não encontrado para o CPF " + cpf);

	}

	// Metodo para atualizar um visitante
	public Visitante atualizarCadastroVisitante(Visitante visitante, Long id) {

		try {
			Visitante atualizar = visitanteRepository.findById(id).get();

			if (atualizar != null) {

				BeanUtils.copyProperties(visitante, atualizar, "id");

				visitanteRepository.save(atualizar);
			}
		} catch (Exception e) {
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado visitante com o ID " + id));
		}

		return null;
	}

	// Metodo para deletarUmVisitante

	public void deletarVisitantePorId(Long id) {

		try {

			visitanteRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de visitante com código %d", id));
		}

	}

}
