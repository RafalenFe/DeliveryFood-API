package com.reneantunes.reneFood.domain.exception;

public class EstadoNaoEncontrataException extends EntidadeNaoEncontrataException {

	
	private static final long serialVersionUID = 1L;
	
	public EstadoNaoEncontrataException(String mensagem) {
		super(mensagem);
		
	}
	
	public EstadoNaoEncontrataException(Long estadoId) {
		this(String.format("Não existe um cadastro de Estado com código %d", estadoId));
	}
	
}
