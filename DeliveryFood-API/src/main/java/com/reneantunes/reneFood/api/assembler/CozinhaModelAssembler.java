package com.reneantunes.reneFood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reneantunes.reneFood.api.model.CozinhaModel;
import com.reneantunes.reneFood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler {
	
	@Autowired
	private ModelMapper modelMpper;
	
	public CozinhaModel toModel(Cozinha cozinha) {
		return modelMpper.map(cozinha, CozinhaModel.class);
	}
	
	public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas){
		
		return cozinhas.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}

}
