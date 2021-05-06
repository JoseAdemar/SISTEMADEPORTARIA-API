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
	@SuppressWarnings("null")
	public Visitante buscarPorCpf(Visitante visitante) {

		Optional<Visitante> buscarVisitante = visitanteRepository.findCpfByCpf(visitante.getCpf());

		if (buscarVisitante != null) {

			return visitanteRepository.findCpfByCpf(visitante.getCpf()).get();
		}
		return buscarVisitante.get();

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

	public Visitante deletarVisitantePorId(Long id) {

		Visitante deletar = visitanteRepository.findById(id).get();
		
		if(deletar != null) {
			
			visitanteRepository.delete(deletar);
		}
		
		return deletar;

	}

}
