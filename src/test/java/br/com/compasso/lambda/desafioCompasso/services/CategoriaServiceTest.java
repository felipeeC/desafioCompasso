package br.com.compasso.lambda.desafioCompasso.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;

class CategoriaServiceTest {

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

		// Verificamos se o resultado da requisição é igual ao esperado
		// Se sim significa que tudo correu bem
		//Assert.assertNotNull(categoria);
		//Assert.assertEquals("Terror", categoria.getFirstName());
		//Assert.assertEquals("Comédia", categoria.getLastName());
	}
}
