package com.reneantunes.reneFood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.reneantunes.reneFood.core.validation.TaxaFrete;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInput {
	@NotBlank
	private String nome;
	@PositiveOrZero
	@TaxaFrete
	private BigDecimal taxaFrete;
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;	
	
	

}
