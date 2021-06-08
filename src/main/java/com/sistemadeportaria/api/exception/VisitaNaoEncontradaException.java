package com.sistemadeportaria.api.exception;

public class VisitaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public VisitaNaoEncontradaException(String mensagem) {
		super(mensagem);

	}

	public VisitaNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de visita com código: %d", id));
	}

}
