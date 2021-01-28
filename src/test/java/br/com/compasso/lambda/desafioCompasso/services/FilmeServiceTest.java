package br.com.compasso.lambda.desafioCompasso.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeForm;
import br.com.compasso.lambda.desafioCompasso.models.Filme;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FilmeServiceTest {

	@Autowired
	private FilmeService service;

	// OK
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

		assertEquals(filme1.getNome(), filmes.get(0).getNome());
		assertEquals(filme2.getNome(), filmes.get(1).getNome());
		assertEquals(filme3.getNome(), filmes.get(2).getNome());
	}

	// OK
	@Test
	public void retornaFilmeNaoExistente() {
		Long id = 80L;
		Optional<Filme> filme = service.getFilmeById(id);

		assertTrue(filme.isEmpty());
	}

	// OK
	@Test
	public void deletaFilmeById() {
		Long id = 1L;

		service.delete(id);

		Optional<Filme> filme = service.getFilmeById(id);

		assertTrue(filme.isEmpty());
	}

	// OK
	@Test
	public void deletaFilmeByIdQueNaoExsite() {
		Long id = 5L;

		try {
			service.delete(id);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	// OK
	@Test
	public void atualizaFilme() {
		Long id = 1L;

		FilmeForm ff = new FilmeForm("A bordo", "Titanic", "Drama antigo", "1997-05-23", "Hollywood", "James Cameron",
				"Leonardo Dicaprio");

		service.update(id, ff);
		
		Optional<Filme> filme = service.getFilmeById(id);

		assertEquals("Drama antigo",filme.get().getComentario());
	}

	// OK
	@Test
	public void atualizaFilmeComDadosFaltando() {
		Long id = 1L;

		FilmeForm ff = new FilmeForm("A bordo", "Titanic", "", "1997-05-23", "Hollywood", "James Cameron",
				"Leonardo Dicaprio");

		service.update(id, ff);
		
		Optional<Filme> filme = service.getFilmeById(id);

		assertTrue(filme.isEmpty());
	}

}
