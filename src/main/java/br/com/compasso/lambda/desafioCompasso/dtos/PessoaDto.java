package br.com.compasso.lambda.desafioCompasso.dtos;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.data.domain.Page;

import br.com.compasso.lambda.desafioCompasso.models.Pessoa;

public class PessoaDto {

	private Long id;
	private String nome;
	private String email;
	private int idade;
	
	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.email = pessoa.getEmail();
		this.idade = Period.between(pessoa.getAniversario(), LocalDate.now()).getYears();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}

	public int getIdade() {
		return idade;
	}
	
	public static Page<PessoaDto> converter(Page<Pessoa> pessoas) {
		return pessoas.map(PessoaDto::new);
	}
	
	
}
