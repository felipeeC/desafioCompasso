package br.com.compasso.lambda.desafioCompasso.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pessoa {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int idade;
	
	@ManyToMany(mappedBy = "pessoas")
	private List<Filme> filmes;



	//Construtor
	public Pessoa(Long id, String nome, int idade, List<Filme> filmes) {
		super();
		this.id = id;
	}
	
	// Construtor
	public Pessoa(String nome, int idade, List<Filme> filmes) {
		this.nome = nome;
		this.idade = idade;
		this.filmes = filmes;
	}

	public Pessoa() {
		super();
	}

	// Getters e Setters
	
	public List<Filme> getFilmes() {
		return filmes;
	}
	
	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return this.id + "\t" + this.nome + "\t\t" + this.idade;
	}

	// Equals

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Pessoa outraPessoa = (Pessoa) obj;

		if (!this.nome.toUpperCase().equals(outraPessoa.nome.toUpperCase()))
			return false;
		if (this.idade != outraPessoa.idade)
			return false;

		return true;

	}
}
