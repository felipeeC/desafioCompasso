package br.com.compasso.lambda.desafioCompasso.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.compasso.lambda.desafioCompasso.models.Filme;

public class FilmeServiceTest {

	@Autowired
	private FilmeService service;

	// CORRIGIR
	@Test
	public void retornaTodosFilmes() throws Exception {

		List<Filme> filmes = service.getFilmes();

		Filme filme1 = new Filme("A bordo", "Titanic", "Filme trágico", "1997-05-23", "Hollywood", "James Cameron",
				"Leonardo Dicaprio");
		Filme filme2 = new Filme(
				"An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
				"Harry Potter e a Pedra Filosofal", "", "2001-11-23", "Warner Bros.", "Chris Columbus",
				"Daniel Radcliffe");
		Filme filme3 = new Filme("tt", "calculadora", "ttttt", "1999-05-12", "Hollywood", "felipe alves",
				"Leonardo Da vinci");

		List<Filme> filmesTeste = new ArrayList<>();

		filmesTeste.add(filme1);
		filmesTeste.add(filme2);
		filmesTeste.add(filme3);

		URI uri = new URI("/filmes");

		assertEquals(filme1.getNome(), filmes.get(0).getNome());
		assertEquals(filme2.getNome(), filmes.get(1).getNome());
		assertEquals(filme3.getNome(), filmes.get(2).getNome());
	}

}
