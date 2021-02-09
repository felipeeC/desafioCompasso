package br.com.compasso.lambda.desafioCompasso.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.compasso.lambda.desafioCompasso.models.Filme;

public class FilmeCompletoDto {

	private Long id;
	private String descricao;
	private String nome;
	private String comentario;
	private String dataLancamento; // ver datetimelocal
	private String estudio;
	private String diretor;
	private String elenco;
	private double avaliacao;

	public FilmeCompletoDto(Filme filme) {
		super();
		this.id = filme.getId();
		this.descricao = filme.getDescricao();
		this.nome = filme.getNome();
		this.comentario = filme.getComentario();
		this.dataLancamento = filme.getDataLancamento();
		this.estudio = filme.getEstudio();
		this.diretor = filme.getDiretor();
		this.elenco = filme.getElenco();
		this.avaliacao = filme.getAvaliacao();
	}

	public double getAvaliacao() {
		return avaliacao;
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

	public String getComentario() {
		return comentario;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public String getEstudio() {
		return estudio;
	}

	public String getDiretor() {
		return diretor;
	}

	public String getElenco() {
		return elenco;
	}

	public static List<FilmeCompletoDto> converter(List<Filme> filmes) {

		return filmes.stream().map(FilmeCompletoDto::new).collect(Collectors.toList());
	}

	public static Page<FilmeCompletoDto> converterPage(Page<Filme> filmes) {
		return filmes.map(FilmeCompletoDto::new);
	}
	
	public static FilmeCompletoDto converter(Filme filme) {

		return filme.map(FilmeCompletoDto::new);
	}

}
