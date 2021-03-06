package br.com.compasso.lambda.desafioCompasso.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String nome;
	private String comentario;
	private String dataLancamento; // ver datetimelocal
	private String estudio;
	private String diretor;
	private String elenco;
	private double avaliacao;

	@ManyToMany
//	@JoinTable(name = "FILME_PESSOAS" ,
//	joinColumns = @JoinColumn(name ="Filme_id"),
//	inverseJoinColumns = @JoinColumn(name = "Pessoa_id"))
	private List<Pessoa> pessoas;

	@ManyToMany
	// @JoinTable(name = "filmeCategoria" , joinColumns = @JoinColumn(name =
	// "filme_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	public Filme() {
		super();

	}

	public Filme(String descricao, String nome) {
		super();
		this.descricao = descricao;
		this.nome = nome;
	}

	public Filme(String descricao, String nome, String comentario, String dataLancamento, String estudio,
			String diretor, String elenco, double avaliacao) {
		super();
		this.descricao = descricao;
		this.nome = nome;
		this.comentario = comentario;
		this.dataLancamento = dataLancamento;
		this.estudio = estudio;
		this.diretor = diretor;
		this.elenco = elenco;
		this.avaliacao = avaliacao;
	}

	// HashCode e Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Filme other = (Filme) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equalsIgnoreCase(other.nome))
			return false;
		return true;
	}

	// Getters e Setters

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public String toString() {
		return this.id + "\t" + this.nome + "\t\t" + this.descricao;
	}
}
