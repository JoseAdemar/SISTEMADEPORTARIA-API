package com.sistemadeportaria.api.execoes;

public class VisitanteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public VisitanteNaoEncontradoException(String mensagem) {
		super(mensagem);

	}
	
	 public VisitanteNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de visisante com o código: %d", id));
	}

}
