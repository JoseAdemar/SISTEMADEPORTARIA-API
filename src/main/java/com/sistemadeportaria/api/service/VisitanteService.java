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

		try {
			if (salvarVisitante.getNome() != null & salvarVisitante.getCpf() != null
					& salvarVisitante.getTelefone() != null) {

				return visitanteRepository.save(visitante);
			}
		} catch (EmptyResultDataAccessException e) {

			throw new EntidadeNaoEncontradaException("Verifique os campos obrigatórios");
		}
		return salvarVisitante;
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
		return buscarCpf.get();

	}

	// Metodo para atualizar um visitante
	public Visitante atualizarCadastroVisitante(Visitante visitante, Long id) {

		Visitante atualizar = visitanteRepository.findById(id).get();

		if (atualizar != null) {

			BeanUtils.copyProperties(visitante, atualizar, "id");

			visitanteRepository.save(atualizar);
		}

		return atualizar;
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
