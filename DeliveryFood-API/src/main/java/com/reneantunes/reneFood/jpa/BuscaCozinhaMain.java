package com.reneantunes.reneFood.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.reneantunes.reneFood.ReneFoodApiAplication;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.repository.CozinhaRepository;

public class BuscaCozinhaMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ReneFoodApiAplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Optional<Cozinha> cozinha =  cozinhaRepository.findById(1L);
		
	
			System.out.println(cozinha.get());
	
		
	}
}
