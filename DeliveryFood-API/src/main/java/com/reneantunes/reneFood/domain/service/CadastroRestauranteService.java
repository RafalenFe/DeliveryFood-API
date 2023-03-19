package com.reneantunes.reneFood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.model.Restaurante;
import com.reneantunes.reneFood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe um cadastro de restaurante com código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
		
	}
	
	@Transactional
	public void excluir(Long restauranteId) {
		
		try {
		restauranteRepository.deleteById(restauranteId);
		}catch(EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontrataException(
					String.format(MSG_ENTIDADE_NAO_ENCONTRADA,restauranteId));
						
		}
	
	}
	
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
	    return restauranteRepository.findById(restauranteId)
	        .orElseThrow(() -> new EntidadeNaoEncontrataException(
	                String.format(MSG_ENTIDADE_NAO_ENCONTRADA, restauranteId)));
	}
}
