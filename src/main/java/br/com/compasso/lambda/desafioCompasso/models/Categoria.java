package br.com.compasso.lambda.desafioCompasso.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String descricao;
	private boolean lancamento;
	private int ano;

	// Construtor

	public Categoria(String descricao, boolean lancamento, int ano) {
		super();
		this.descricao = descricao;
		this.lancamento = lancamento;
		this.ano = ano;
	}

	// Getters e Setters

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isLancamento() {
		return lancamento;
	}

	public void setLancamento(boolean lancamento) {
		this.lancamento = lancamento;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
