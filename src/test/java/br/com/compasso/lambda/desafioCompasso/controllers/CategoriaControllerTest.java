package br.com.compasso.lambda.desafioCompasso.controllers;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compasso.lambda.desafioCompasso.dtos.CategoriaForm;
import br.com.compasso.lambda.desafioCompasso.models.Categoria;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CategoriaControllerTest {

	final static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	// OK
	@Test
	public void testCadastroCategoriaNomeVazio() throws Exception {

		CategoriaForm categoriaForm = new CategoriaForm();

		categoriaForm.setNome("");

		URI uri = new URI("/categorias");

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(asJsonString(categoriaForm))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
	}

	// OK
	@Test
	public void testCadastroCategoriaNomeNulo() throws Exception {

		CategoriaForm categoriaForm = new CategoriaForm();

		URI uri = new URI("/categorias");

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(asJsonString(categoriaForm))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
	}

	// OK
	@Test
	public void testCadastroCategoriaPossuiNome() throws Exception {

		CategoriaForm categoriaForm = new CategoriaForm();

		categoriaForm.setNome("Familia");

		URI uri = new URI("/categorias");

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(asJsonString(categoriaForm))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
	}

	// OK
	@Test
	public void testDeleteCategoria() throws Exception {

		Categoria idCategoria = new Categoria();

		idCategoria.setId(0);

		mockMvc.perform( MockMvcRequestBuilders.delete("/categorias/{id}", 1) )
        .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NO_CONTENT.value()));
	}

	// OK
	@Test
	public void DevolveFilmesPorCategoria() throws Exception {

		URI uri = new URI("/categorias/filmes/2");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	// OK
	@Test
	public void DevolveFilmesPorCategoriaQueNaoExiste() throws Exception {

		URI uri = new URI("/categorias/filmes/15");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}

	// OK
	@Test
	public void DevolveCategorias() throws Exception {

		URI uri = new URI("/categorias");

		mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	public static String asJsonString(final Object obj) {
		try {
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
