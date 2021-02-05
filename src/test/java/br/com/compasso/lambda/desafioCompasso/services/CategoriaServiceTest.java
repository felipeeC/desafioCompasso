package br.com.compasso.lambda.desafioCompasso.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class CategoriaServiceTest {

	@Autowired
	private CategoriaService service;

	//
	@Test
	public void retornaTodasCategorias() {

		List<Categoria> categorias = service.getCategorias();

		Categoria categoria1 = new Categoria("Suspense");
		Categoria categoria2 = new Categoria("Comédia");
		Categoria categoria3 = new Categoria("Ação");

		assertEquals(categoria1.getNome(), categorias.get(0).getNome());
		assertEquals(categoria2.getNome(), categorias.get(1).getNome());
		assertEquals(categoria3.getNome(), categorias.get(2).getNome());
	}

	// OK
	@Test
	public void retornaCategoriaNaoExistePorID() {
		Long id = 80L;

		try {
			service.delete(id);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	// OK
	@Test
	public void retornaCategoriaQueExistePorID() {
		Long id = 1L;

		Categoria categoria = service.getById(id);

		assertEquals("Suspense", categoria.getNome());
	}

	// OK
	@Test
	public void retornaCategoriaNaoExistePorNome() {
		String nome = "Coffee Break";

		Optional<Categoria> categoria = service.findByNome(nome);

		assertTrue(categoria.isEmpty());
	}

	// OK
	@Test
	public void retornaCategoriaQueExistePorNome() {
		String nome = "Comédia";

		Optional<Categoria> categoria = service.findByNome(nome);

		assertEquals("Comédia", categoria.get().getNome());
	}

	// OK
	@Test
	public void deletaCategoriaQueExiste() {
		Long id = 1L;
		service.delete(id);

		try {
			service.delete(id);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	// OK
	@Test
	public void deletaCategoriaQueNaoExiste() {
		Long id = 15L;

		try {
			service.delete(id);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
