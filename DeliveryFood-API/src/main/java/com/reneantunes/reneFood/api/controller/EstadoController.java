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

import com.reneantunes.reneFood.api.assembler.EstadoInputDisassembler;
import com.reneantunes.reneFood.api.assembler.EstadoModelAssembler;
import com.reneantunes.reneFood.api.model.EstadoModel;
import com.reneantunes.reneFood.api.model.input.EstadoInput;
import com.reneantunes.reneFood.domain.exception.EntidadeEmUsoException;
import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.model.Estado;
import com.reneantunes.reneFood.domain.repository.EstadoRepository;
import com.reneantunes.reneFood.domain.service.CadastroEstadoService;



@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadodoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler; 
	
	@GetMapping
	public List<EstadoModel> listar(){
		
		List<Estado> todosEstados = estadodoRepository.findAll();
		
		return  estadoModelAssembler.toCollectionModel(todosEstados);
		
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado esdado = cadastroEstadoService.buscarOufalhar(estadoId);
		
		return estadoModelAssembler.toModel(esdado) ;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		
		estado = cadastroEstadoService.salvar(estado);
		
		return estadoModelAssembler.toModel(estado);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput){
			Estado estadoAtual = cadastroEstadoService.buscarOufalhar(estadoId);
			estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
			Estado estado = cadastroEstadoService.salvar(estadoAtual);
			
			return estadoModelAssembler.toModel(estado); 
	}
	
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId){
		cadastroEstadoService.excluir(estadoId);
	}
	
	
}
