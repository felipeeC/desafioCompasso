package br.com.compasso.lambda.desafioCompasso.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;

@SpringBootTest

class CategoriaServiceTest {
	
	@Autowired
	private CategoriaService service;
	
	@Test
	public void retornaTodasCategorias() {

		List<Categoria> categorias = service.getCategorias();

		Categoria categoria1 = new Categoria("Suspense");
		Categoria categoria2 = new Categoria("Comédia");
		Categoria categoria3 = new Categoria("Ação");
		
		List<Categoria> categoriasTeste = new ArrayList<>();

		categoriasTeste.add(categoria1);
		categoriasTeste.add(categoria2);
		categoriasTeste.add(categoria3);

		assertEquals(categoria1.getNome(), categorias.get(0).getNome());
		assertEquals(categoria2.getNome(), categorias.get(1).getNome());
		assertEquals(categoria3.getNome(), categorias.get(2).getNome());
	}

}
