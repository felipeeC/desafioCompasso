package br.com.compasso.lambda.desafioCompasso.dtos;

import org.springframework.data.domain.Page;

import br.com.compasso.lambda.desafioCompasso.models.Pessoa;

public class PessoaDto {

	private Long id;
	private String nome;
	private int idade;
	
	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.idade = pessoa.getIdade();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}
	
	public static Page<PessoaDto> converter(Page<Pessoa> pessoas) {
		return pessoas.map(PessoaDto::new);
	}
	
	
}
