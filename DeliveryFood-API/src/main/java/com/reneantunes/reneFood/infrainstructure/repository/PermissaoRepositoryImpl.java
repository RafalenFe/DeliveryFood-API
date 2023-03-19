package com.reneantunes.reneFood.infrainstructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.reneantunes.reneFood.domain.model.Permissao;
import com.reneantunes.reneFood.domain.repository.PermissaoRepository;

public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<Permissao> listar() {
		
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		
		return manager.find(Permissao.class, id);
	}
	
	@Transactional
	@Override
	public Permissao salvar(Permissao estado) {
		
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public void remover(Permissao estado) {
		estado =  buscar(estado.getId());
		manager.remove(estado);
		
	}

}
