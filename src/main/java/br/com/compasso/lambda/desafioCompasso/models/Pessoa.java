package br.com.compasso.lambda.desafioCompasso.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pessoa {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDate aniversario;
	private String email;
	private String senha;

	@ManyToMany(mappedBy = "pessoas")
	private List<Filme> filmes = new ArrayList<>();
	// Construtor

	public Pessoa() {
		super();
	}

	public Pessoa(String nome, LocalDate aniversario, String email, String senha) {
		this.nome = nome;
		this.aniversario = aniversario;
		this.email = email;
		this.senha= senha;
	}

	// Getters e Setters

	public List<Filme> getFilmes() {
		return filmes;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public LocalDate getAniversario() {
		return aniversario;
	}

	public String getEmail() {
		return email;
	}

	public void setAniversario(LocalDate aniversario) {
		this.aniversario = aniversario;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	@Override
	public String toString() {
		return "Pessoa [id=" + id + 
				", nome=" + nome + 
				", aniversario=" + aniversario + 
				", email=" + email + 
				", filmes="	+ filmes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aniversario == null) ? 0 : aniversario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (aniversario == null) {
			if (other.aniversario != null)
				return false;
		} else if (!aniversario.equals(other.aniversario))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
