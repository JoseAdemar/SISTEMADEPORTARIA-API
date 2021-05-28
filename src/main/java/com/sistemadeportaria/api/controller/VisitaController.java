package com.sistemadeportaria.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sistemadeportaria.api.execoes.EntidadeNaoEncontradaException;
import com.sistemadeportaria.api.model.Visita;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.service.VisitaService;

@RestController
@RequestMapping("/visitas")
public class VisitaController {

	@Autowired
	private VisitaService visitaService;

	@GetMapping // Lista todas as visitas
	public ResponseEntity<List<Visita>> listaVisitas() {

		List<Visita> listaVisita = visitaService.listarVisitas();

		return ResponseEntity.status(HttpStatus.OK).body(listaVisita);
	}

	@GetMapping("/busca") // Metodo para fazer uma busca dinamica
	public ResponseEntity<?> listaVisitasDinamicamente(String setor, LocalDateTime dataDaVisita, Visitante visitante) {

		List<Visita> buscaVisitas = visitaService.buscaDinamica(setor, dataDaVisita, visitante);

		return ResponseEntity.status(HttpStatus.OK).body(buscaVisitas);

	}

	@PostMapping // Metodo para cadastrar uma visita
	public ResponseEntity<?> cadastroVisita(@Valid @RequestBody Visita visita) {

		Visita visitas = visitaService.cadastrarVisita(visita);

		return ResponseEntity.status(HttpStatus.CREATED).body(visitas);

	}

	@DeleteMapping("/{id}") // Metodo para deletar VISITA por ID
	public ResponseEntity<?> deletaVisitaPorId(@PathVariable Long id) {

		try {
			visitaService.deletarVisitaPorId(id);

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{id}") // Metodo para atualizar uma Visita por ID
	public ResponseEntity<?> atualizaVisita(@PathVariable Long id, @Valid @RequestBody Visita visita) {

		try {
			Visita atualiza = visitaService.atualizarVisita(visita, id);

			return ResponseEntity.status(HttpStatus.OK).body(atualiza);

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
