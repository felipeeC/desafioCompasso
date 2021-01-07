package br.com.compasso.lambda.desafioCompasso.services;

import java.util.ArrayList;
import java.util.List;

import br.com.compasso.lambda.desafioCompasso.models.Filme;

public class FilmeService {

	private List<Filme> filmes = new ArrayList<Filme>();

	// Métodos

	public void imprimeById(int idFilme) {
		for (Filme filme : filmes) {
			if (filme.getId() == idFilme) {
				System.out.println(filme);
			}
		}
	}

	public boolean imprimeByName(String nomeFilme) {
		for (Filme f : filmes) {
			if (f.getNome() == nomeFilme.toUpperCase()) {
				return true;
			}
		}

		System.out.println("Filme não encontrado");
		return false;
	}

	public boolean removeById(int idFilme) {
		for (Filme f : filmes) {
			if (f.getId() == idFilme) {
				filmes.remove(f);
				return true;
			}

		}
		System.out.println("Id não encontrado");
		return false;
	}

	public void adiciona(String descricao, String nome) {
		Filme filme = new Filme();

		if(filmes.contains(filme)) {
			System.out.println("Filme já existe!");
		} else {
			filmes.add(filme);
			System.out.println("Filme adicionado com sucesso!");
		}

	}

	public List<Filme> imprimeTodos() {
		return filmes;
	}

	public boolean verificaById(int id) {
		for (Filme filme : filmes) {
			if (filme.getId() == id) {
				return true;
			}
		}

		System.out.println("Filme com esse id já existe!");
		return false;

	}
}
