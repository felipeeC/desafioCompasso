package br.com.compasso.lambda.desafioCompasso.dtos;



import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.compasso.lambda.desafioCompasso.models.Filme;

public class FilmeForm {
	
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String comentario;
	@NotNull @NotEmpty
	private String dataLancamento;
	@NotNull @NotEmpty
	private String estudio;
	@NotNull @NotEmpty
	private String diretor;
	@NotNull @NotEmpty
	private String elenco;

	
	


	public FilmeForm(@NotEmpty String descricao, @NotEmpty String nome, @NotEmpty String comentario,
			@NotEmpty String dataLancamento, @NotEmpty String estudio, @NotEmpty String diretor,
			@NotEmpty String elenco) {
		this.descricao = descricao;
		this.nome = nome;
		this.comentario = comentario;
		this.dataLancamento = dataLancamento;
		this.estudio = estudio;
		this.diretor = diretor;
		this.elenco = elenco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getElenco() {
		return elenco;
	}

	public void setElenco(String elenco) {
		this.elenco = elenco;
	}

	public Filme converter() {

		return new Filme(descricao, nome, comentario, dataLancamento, estudio, diretor, elenco);
	}
}
