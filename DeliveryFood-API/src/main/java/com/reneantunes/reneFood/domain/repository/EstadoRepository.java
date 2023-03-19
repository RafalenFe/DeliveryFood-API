package com.reneantunes.reneFood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reneantunes.reneFood.domain.model.Estado;

@Repository
public interface EstadoRepository  extends JpaRepository<Estado, Long> {
	
}
