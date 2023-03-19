package com.reneantunes.reneFood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reneantunes.reneFood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
	
}

