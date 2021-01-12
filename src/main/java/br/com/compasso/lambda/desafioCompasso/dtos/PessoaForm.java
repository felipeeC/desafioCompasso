package br.com.compasso.lambda.desafioCompasso.dtos;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.compasso.lambda.desafioCompasso.models.Pessoa;

public class PessoaForm {
	
	@NotNull @NotEmpty @Length(min = 5, max = 60)
	private String nome;
	
	@NotNull @DecimalMax(value = "110") @DecimalMin(value = "0")
	private int idade;
	
	public Pessoa converter() {
		return new Pessoa(nome, idade);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
}
