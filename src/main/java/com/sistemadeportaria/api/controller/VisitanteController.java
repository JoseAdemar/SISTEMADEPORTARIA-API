package com.sistemadeportaria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.service.VisitanteService;

@RestController
@RequestMapping("/visitantes")
public class VisitanteController {

	 @Autowired
	 private VisitanteService visitanteService;
	 
	@PostMapping
	public ResponseEntity<Visitante> cadastroDeVisitante(@Valid @RequestBody Visitante visitante) {
		
		  Visitante salvaVisitante = visitanteService.cadastrarVisitante(visitante);
		  
		  return ResponseEntity.status(HttpStatus.CREATED).body(salvaVisitante);
		  
		  
		
	}
	
	@GetMapping
	public ResponseEntity<List<Visitante>> listaDeVisitantes(){
		
		 List<Visitante> listaVisitante = visitanteService.ListarTodosVisitantes();
		 
		 return ResponseEntity.status(HttpStatus.OK).body(listaVisitante);
	}
	
	@GetMapping("/busca-cpf")
	public ResponseEntity<Visitante> buscaVisitantePorCpf(@Param("busca-cpf")String cpf){
		
		   Visitante visitanteBuscaCpf = visitanteService.buscarPorCpf(cpf);
		   
		   return ResponseEntity.status(HttpStatus.OK).body(visitanteBuscaCpf);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Visitante> atualizarCadastroVisitante(@PathVariable Long id, @RequestBody Visitante visitante){
		
		Visitante atualizaVisitante = visitanteService.atualizarCadastroVisitante(visitante, id);
		
		return ResponseEntity.status(HttpStatus.OK).body(atualizaVisitante);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Visitante> deletaVisitante(@PathVariable Long id) {
		
		Visitante deletarVisitante = visitanteService.deletarVisitantePorId(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletarVisitante);
	}
}









