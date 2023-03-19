package com.reneantunes.reneFood.domain.repository;

import java.util.List;

import com.reneantunes.reneFood.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao estado);
	void remover(Permissao estado);
}

