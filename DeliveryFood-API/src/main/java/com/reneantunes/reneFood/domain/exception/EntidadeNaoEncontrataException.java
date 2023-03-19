package com.reneantunes.reneFood.domain.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontrataException extends NegocioException {

	
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontrataException(String mensagem) {
		super(mensagem);
		
	}
}
