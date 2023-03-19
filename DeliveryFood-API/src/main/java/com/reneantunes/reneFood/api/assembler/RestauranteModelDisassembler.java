package com.reneantunes.reneFood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.reneantunes.reneFood.api.model.input.RestauranteInput;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.model.Restaurante;

@Component
public class RestauranteModelDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput ) {
		
		return modelMapper.map(restauranteInput,Restaurante.class);
		
	}
	
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		// To avoid error: org.hibernate.HibernateException: identifier of an instance of
		//com.reneantunes.reneFood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInput, restaurante);
		
	}
	

}
