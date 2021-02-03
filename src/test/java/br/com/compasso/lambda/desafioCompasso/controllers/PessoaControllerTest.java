package br.com.compasso.lambda.desafioCompasso.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.hamcrest.Matchers;
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

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaForm;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PessoaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	final static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void testCadastroPessoaNomeVazio() throws Exception {
		
		PessoaForm pessoaForm = new PessoaForm();
		
		pessoaForm.setNome("");
		pessoaForm.setAniversario("2000-01-01");
		pessoaForm.setEmail("abc@gmail.com");

		URI uri = new URI("/pessoas");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(pessoaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
		  
	}
	
	@Test
	void testCadastroPessoaNomeNulo() throws Exception {
		
		PessoaForm pessoaForm = new PessoaForm();
		pessoaForm.setAniversario("2000-01-01");
		pessoaForm.setEmail("abc@gmail.com");

		URI uri = new URI("/pessoas");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(pessoaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
		  
		 
	}
	
	@Test
	void testCadastroPessoaIdadeNulo() throws Exception {
		
		PessoaForm pessoaForm = new PessoaForm();
		pessoaForm.setNome("Guilherme");
		
		// setting fields for the NewObject  
		URI uri = new URI("/pessoas");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(pessoaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
		  
		 
	}
	
	@Test
	void testCadastroPessoaIdadeNegativa() throws Exception {
		
		PessoaForm pessoaForm = new PessoaForm();
		pessoaForm.setNome("Guilherme");
		pessoaForm.setAniversario("2030-01-01");
		pessoaForm.setEmail("abc@gmail.com");
		
		// setting fields for the NewObject  
		URI uri = new URI("/pessoas");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(pessoaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
		  
		 
	}
	
	@Test
	void testCadastroPessoaIdadeMaiorQuePermitido() throws Exception {
		
		PessoaForm pessoaForm = new PessoaForm();
		pessoaForm.setNome("Guilherme");
		pessoaForm.setAniversario("1900-01-01");
		pessoaForm.setEmail("abc@gmail.com");
		
		// setting fields for the NewObject  
		URI uri = new URI("/pessoas");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(pessoaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
		  
		 
	}
	
	@Test
	void testCadastroPessoaCompleto() throws Exception {
		
		PessoaForm pessoaForm = new PessoaForm();
		// setting fields for the NewObject  

		pessoaForm.setNome("Moacir");
		pessoaForm.setAniversario("2000-01-01");
		pessoaForm.setEmail("abc@gmail.com");
		
		URI uri = new URI("/pessoas");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(pessoaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
		 
	}

	@Test
	void testGetPessoa() throws Exception {
		URI uri = new URI("/pessoas/1");
		
		mockMvc.perform(get(uri)
		.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome", Matchers.is("Guilherme")))
			.andExpect(jsonPath("$.idade", Matchers.is(24)));
	}
	
	@Test
	void testGetTodasPessoas() throws Exception {
		URI uri = new URI("/pessoas");
		
		mockMvc.perform(get(uri)
		.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content[0].nome", Matchers.is("Guilherme")))
			.andExpect(jsonPath("$.content[0].idade", Matchers.is(24)));
	}
	
	@Test
	void testDeletePessoa() throws Exception {
		URI uri = new URI("/pessoas/1");
		
		mockMvc.perform(delete(uri)
		.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}
	
	@Test
	void testeAlterarPessoa() throws Exception {
		URI uri = new URI("/pessoas/2");
		
		PessoaForm pessoaForm = new PessoaForm();
		pessoaForm.setNome("Marcelo");
		pessoaForm.setAniversario("2000-01-01");
		pessoaForm.setEmail("abc@gmail.com");
		
		mockMvc.perform(
					put(uri)
					.content(asJsonString(pessoaForm))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
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
