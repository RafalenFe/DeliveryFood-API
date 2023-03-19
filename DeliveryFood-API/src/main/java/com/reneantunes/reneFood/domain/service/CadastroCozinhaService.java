package com.reneantunes.reneFood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reneantunes.reneFood.domain.exception.EntidadeEmUsoException;
import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	private static final String MSG_COZINHA_NAO_ENCONTRADA
	= "Não existe um cadastro de cozinha com código %d";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	@Transactional
	public void excluir(Long cozinhaId) {
		try {
		cozinhaRepository.deleteById(cozinhaId);
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrataException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
		
		}catch(DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
		
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontrataException(
						String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
	}
		
	
}
