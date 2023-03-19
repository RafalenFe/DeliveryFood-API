package com.reneantunes.reneFood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.reneantunes.reneFood.ReneFoodApiAplication;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(ReneFoodApiAplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	
	CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
	
	Cozinha cozinha1 = new Cozinha();
	cozinha1.setNome("Brasileira");

	Cozinha cozinha2 = new Cozinha();
	cozinha2.setNome("Japonesa");
	
	
	cozinhaRepository.save(cozinha1);
	cozinhaRepository.save(cozinha2);
	
	
	}
}
