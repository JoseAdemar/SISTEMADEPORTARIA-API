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

import com.sistemadeportaria.api.execoes.EntidadeNaoEncontradaException;
import com.sistemadeportaria.api.model.Visitante;
import com.sistemadeportaria.api.service.VisitanteService;

@RestController
@RequestMapping("/visitantes")
public class VisitanteController {

	@Autowired
	private VisitanteService visitanteService;

	//Cadastrar um novo visitante
	@PostMapping
	public ResponseEntity<?> cadastroDeVisitante(@Valid @RequestBody Visitante visitante) {

		try {
		Visitante salvaVisitante = visitanteService.cadastrarVisitante(visitante);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaVisitante);
		
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	//Listar todos
	@GetMapping
	public ResponseEntity<List<Visitante>> listaDeVisitantes() {

		List<Visitante> listaVisitante = visitanteService.ListarTodosVisitantes();

		return ResponseEntity.status(HttpStatus.OK).body(listaVisitante);
	}

	//Fazer consulta por CPF
	@GetMapping("/busca-por-cpf")
	public ResponseEntity<?> buscaVisitantePorCpf(@Valid @Param(value = "nome") String nome,
			@Param(value = "cpf")String cpf, 
			@Param(value = "telefone")String telefone) {

		try {
		Visitante visitanteBuscaCpf = visitanteService.buscarPorCpf(nome,cpf,telefone);

		return ResponseEntity.status(HttpStatus.OK).body(visitanteBuscaCpf);
		
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
	}

	//Atualizar por id
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCadastroVisitante(@PathVariable Long id,
			@Valid @RequestBody Visitante visitante) {

		try {
		Visitante atualizaVisitante = visitanteService.atualizarCadastroVisitante(visitante, id);

		return ResponseEntity.status(HttpStatus.OK).body(atualizaVisitante);
		
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	//Deletar por id
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaVisitante(@PathVariable Long id) {

		try {
			visitanteService.deletarVisitantePorId(id);

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
}
