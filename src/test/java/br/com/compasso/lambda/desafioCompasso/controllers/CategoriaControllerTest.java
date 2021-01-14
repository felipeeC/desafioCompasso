package br.com.compasso.lambda.desafioCompasso.controllers;

import static org.junit.jupiter.api.Assertions.*;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CategoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCadastroCategoriaNomeVazio() throws Exception {
		
		CategoriaForm categoriaForm = new CategoriaForm();
		
		categoriaForm.setNome("");
		// setting fields for the NewObject  

		URI uri = new URI("/categorias");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(categoriaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NO_CONTENT.value()));
		  
		 
	}
	
	@Test
	public void testCadastroCategoriaNomeNulo() throws Exception {
		
		CategoriaForm categoriaForm = new CategoriaForm();
				
		// setting fields for the NewObject  
		URI uri = new URI("/categorias");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(categoriaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NO_CONTENT.value()));
		  
		 
	}
	
	@Test
	public void testCadastroCategoriaPossuiNome() throws Exception {
		
		CategoriaForm categoriaForm = new CategoriaForm();
		// setting fields for the NewObject  

		categoriaForm.setNome("Terror");
		
		URI uri = new URI("/categorias");
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
		  .content(asJsonString(categoriaForm))
		  .contentType(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
		  
		 
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  

}
