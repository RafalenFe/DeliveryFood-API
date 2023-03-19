package com.reneantunes.reneFood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.reneantunes.reneFood.ReneFoodApiAplication;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ReneFoodApiAplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		List<Cozinha> cozinhas =  cozinhaRepository.findAll();
				
		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}
		
	}
}
