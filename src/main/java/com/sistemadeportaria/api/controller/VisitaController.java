package com.sistemadeportaria.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.service.VisitaService;

@RestController
@RequestMapping("/visitas")
public class VisitaController {

	@Autowired
	private VisitaService visitaService;

	@GetMapping
	public ResponseEntity<List<Visita>> listaVisitas() {

		List<Visita> listaVisita = visitaService.listarVisitas();

		return ResponseEntity.status(HttpStatus.OK).body(listaVisita);
	}

	@PostMapping
	public ResponseEntity<Visita> cadastroVisita(@Valid @RequestBody Visita visita){
		
		   Visita visitas = visitaService.cadastrarVisita(visita);
		   
		   return ResponseEntity.status(HttpStatus.CREATED).body(visitas);
	}
	

	@GetMapping("/{busca}")
	public ResponseEntity<Visita> buscarVisita(@Param(value = "busca") LocalDateTime visitaData){
		
		Visita buscaVisitas = visitaService.pesquisarVisitaPorDataECpf(visitaData);
		
		return ResponseEntity.status(HttpStatus.OK).body(buscaVisitas);
		
	}
}
