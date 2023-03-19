package com.reneantunes.reneFood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.reneantunes.reneFood.api.assembler.CozinhaInputDisassembler;
import com.reneantunes.reneFood.api.assembler.CozinhaModelAssembler;
import com.reneantunes.reneFood.api.model.CozinhaModel;
import com.reneantunes.reneFood.api.model.input.CozinhaInput;
import com.reneantunes.reneFood.domain.exception.EntidadeEmUsoException;
import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.model.Cozinha;
import com.reneantunes.reneFood.domain.repository.CozinhaRepository;
import com.reneantunes.reneFood.domain.service.CadastroCozinhaService;


@RestController
@RequestMapping(value = "/cozinhas") // , produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@GetMapping
	public List<CozinhaModel> listar () {
		
		List<Cozinha> todasCozinhas = cozinhaRepository.findAll();
		
		return cozinhaModelAssembler.toCollectionModel(todasCozinhas);
		
	}
	
	
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		
		return cozinhaModelAssembler.toModel(cozinha);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput){
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		
		cozinha = cadastroCozinhaService.salvar(cozinha);
		
		return cozinhaModelAssembler.toModel(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
		
		Cozinha	cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		
		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		
		cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);
		
				
		return cozinhaModelAssembler.toModel(cozinhaAtual) ;
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinhaService.excluir(cozinhaId);
	}
	
	
	
	
//	@DeleteMapping("/{cozinhaId}")
//	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
//		try {
//			
//			cadastroCozinha.excluir(cozinhaId);
//			
//			return ResponseEntity.noContent().build();
//		
//		}catch(EntidadeNaoEncontrataException e) {
//			
//			return ResponseEntity.notFound().build();
//			
//		}catch(EntidadeEmUsoException e) {
//			
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//		}
//	}
	
//	@GetMapping("/{cozinhaId}")
//	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
//		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
//		
////		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
////		return ResponseEntity.ok(cozinha);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//		
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
//	
//	}
	
	
}
