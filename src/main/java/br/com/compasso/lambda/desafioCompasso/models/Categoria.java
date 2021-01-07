package br.com.compasso.lambda.desafioCompasso.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@ManyToMany(mappedBy = "categorias")
	private List<Filme> filmes = new ArrayList<>();

	// Construtor
	
	public Categoria() {
		
	}

	public Categoria(String nome) {
		
		this.nome = nome;

	}
	
	// Getters e Setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}