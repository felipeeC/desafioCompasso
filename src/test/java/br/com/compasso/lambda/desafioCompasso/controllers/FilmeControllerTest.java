package br.com.compasso.lambda.desafioCompasso.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeForm;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class FilmeControllerTest {

//	@Autowired
//	private FilmeService service;

	@Autowired
	private MockMvc mockMvc;

	static final ObjectMapper mapper = new ObjectMapper();

	// OK
	@Test
	public void cadastraFilmeVazio() throws Exception {

		FilmeForm ff = new FilmeForm(null, null, null, null, null, null, null,0);

		ff.setNome("");

		URI uri = new URI("/filmes");

		mockMvc.perform(
				MockMvcRequestBuilders.post(uri).content(asJsonString(ff)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));

//		service.postFilme(filme);
//
//		Long id = 4L;
//
//		Optional<Filme> filmeTest = service.getFilmeById(id);
//
//		System.out.println(filmeTest);

//		assertTrue(filmeTest.isEmpty());
	}

	// OK
	@Test
	public void adicionaCategoriaNoFilme() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/1/associar-categoria/1");

		mockMvc.perform(post(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.CREATED.value()));
	}

	// OK
	@Test
	public void adicionaCategoriaDuplicadaNoFilme() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/1/associar-categoria/1");

		mockMvc.perform(post(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.CONFLICT.value()));

	}

	// OK
	@Test
	@Order(1)
	public void adicionaPessoaNoFilme() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/2/associar-pessoa/1");

		mockMvc.perform(post(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.CREATED.value()));
	}

	// OK
	@Test
	@Order(2)
	public void adicionaPessoaDuplicadaNoFilme() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/2/associar-pessoa/1");

		mockMvc.perform(post(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.CONFLICT.value()));
	}

	// OK
	@Test
	public void pegaIdPessoaFilmeInexistente() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/mylist/5");

		mockMvc.perform(MockMvcRequestBuilders.get(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}

	// OK
	@Test
	public void pegaPessoaFilmeVazio() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/mylist/1");

		mockMvc.perform(MockMvcRequestBuilders.get(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.OK.value()));
	}

	// OK
	@Test
	public void deletaFilmeInexistenteDeFilmePessoa() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/3/desassociar-pessoa/1");

		mockMvc.perform(MockMvcRequestBuilders.delete(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}

	// OK
	@Test
	@Order(3)
	public void deletaFilmeExistenteDeFilmePessoa() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/2/desassociar-pessoa/1");

		mockMvc.perform(MockMvcRequestBuilders.delete(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NO_CONTENT.value()));
	}

	// OK
	@Test
	public void deletaFilmeNaoExiste() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/5");

		mockMvc.perform(MockMvcRequestBuilders.delete(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}

	// OK
	@Test
	public void deletaFilmeQueExiste() throws Exception {
		URI uriCategoriaFilme = new URI("/filmes/1");

		mockMvc.perform(MockMvcRequestBuilders.delete(uriCategoriaFilme).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NO_CONTENT.value()));
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
