package br.com.compasso.lambda.desafioCompasso.dtos;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public static List<PessoaDto> converter(List<Pessoa> pessoas) {
		return pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
	}
	
	
}
