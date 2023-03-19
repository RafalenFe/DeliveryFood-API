package com.reneantunes.reneFood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.reneantunes.reneFood.ReneFoodApiAplication;
import com.reneantunes.reneFood.domain.model.Restaurante;
import com.reneantunes.reneFood.domain.repository.RestauranteRepository;

public class TesteRestauranteMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ReneFoodApiAplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
		
	RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
	List<Restaurante> todosRestaurantes = restauranteRepository.findAll();
	
	for (Restaurante restaurante : todosRestaurantes) {
		
		System.out.printf("%s - %f - %s\n", restaurante.getNome(),
				restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
	}
	
		
			
	
	
			
	
		
	}
}
