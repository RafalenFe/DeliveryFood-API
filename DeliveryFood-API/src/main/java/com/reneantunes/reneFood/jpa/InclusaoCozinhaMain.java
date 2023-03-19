package com.reneantunes.reneFood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.reneantunes.reneFood.ReneFoodApiAplication;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(ReneFoodApiAplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
	
	Cozinha cozinha = new Cozinha();
	cozinha.setId(1L);
	cozinha.setNome("Brasileira");

	cozinhaRepository.save(cozinha);
	
	
	}
}
