package br.com.compasso.lambda.desafioCompasso.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.compasso.lambda.desafioCompasso.models.Filme;

public class FilmeDto {

	private Long id;
	private String descricao;
	private String nome;

	public FilmeDto(Filme filme) {
		this.id = filme.getId();
		this.descricao = filme.getDescricao();
		this.nome = filme.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public static List<FilmeDto> converter(List<Filme> filmes) {
		return filmes.stream().map(FilmeDto::new).collect(Collectors.toList());
	}
	public static Page<FilmeDto> converterPage(Page<Filme> filmes) {
		return filmes.map(FilmeDto::new);
	}
}
