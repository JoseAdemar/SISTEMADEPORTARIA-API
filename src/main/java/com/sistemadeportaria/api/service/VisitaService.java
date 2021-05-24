package com.sistemadeportaria.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sistemadeportaria.api.execoes.EntidadeNaoEncontradaException;
import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.repository.VisitaRepository;

@Service
public class VisitaService {

	@Autowired
	private VisitaRepository visitaRepository;

	// Metodo para listar todas as visitas
	public List<Visita> listarVisitas() {

		return visitaRepository.findAll();
	}
	// Metodo para realizar uma consulta dinamica
	public List<Visita> buscaDinamica(LocalDateTime dataDaVisita, String setor, Visitante visitante){
		
		return visitaRepository.find(dataDaVisita, setor, visitante);
	}

	// Metodo para cadastrar uma visita
	public Visita cadastrarVisita(Visita visita) {

		Visita visitas = visitaRepository.save(visita);

		if (visitas != null) {

			return visitas;
		}

		throw new EntidadeNaoEncontradaException(String.format("Não foi possível realizar o cadastro"));

	}

	// Metodo para deletar Visita por ID
	public void deletarVisitaPorId(Long id) {

		try {
			visitaRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new EntidadeNaoEncontradaException(String.format("Visita não encontrada para o id: " + id));
		}

	}
	
	// Metodo para atualizar uma Visita por ID
	public Visita atualizarVisita(Visita visita, Long id) {
		
		  try {
		  Visita atualiza = visitaRepository.findById(id).get();
		  
		  if(atualiza != null) {
			  
			  BeanUtils.copyProperties(visita, atualiza, "id");
			  
			 return  visitaRepository.save(atualiza);
		  }
		  }catch (Exception e) {
			
			  throw new EntidadeNaoEncontradaException
			  (String.format("Não foi encontrada visita com o id: " + id));
		}
		  
		  return null;
	}

}
