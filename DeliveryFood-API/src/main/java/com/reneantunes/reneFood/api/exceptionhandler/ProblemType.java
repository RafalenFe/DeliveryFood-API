package com.reneantunes.reneFood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensível", "Mensagem incompreensível"),
	
	RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrada",
			"Recusrso não encotrada"),

	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	
	ERRO_NEGOCIO("/erro-negocio", "Violação regra de negócio"),
	
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");
	
	
	
	
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "http://renefood.com.br" + path;
		this.title = title;
	}
}
