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

	@Test
	public void testFindOne() throws Exception {

		// Fazemos uma requisição HTTP GET buscando por todas as categorias
		//String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);

		// Convertemos a resposta JSON para Objeto usando op Jackson
		//List<Categoria> categorias = MAPPER.readValue(response,
				//MAPPER.getTypeFactory().constructCollectionType(List.class, Categoria.class));
		// Pegamos o ID da categoria na posição 1 da lista e fazemos nova requisição
		// Recuperando as informações da mesma
		//Categoria categoria = restTemplate.getForObject(BASE_PATH + "/" + categorias.get(1).getIdCategoria(), Categoria.class);

		 //Verificamos se o resultado da requisição é igual ao esperado
		 //Se sim significa que tudo correu bem
		 //Assert.assertNotNull(categoria);
		 //Assert.assertEquals("Terror", categoria.getFirstName());
		 //Assert.assertEquals("Comédia", categoria.getLastName());
	}
}
