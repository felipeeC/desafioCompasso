package br.com.compasso.lambda.desafioCompasso.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.CategoriaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.CategoriaForm;
import br.com.compasso.lambda.desafioCompasso.models.Categoria;
import br.com.compasso.lambda.desafioCompasso.repositories.CategoriaRepository;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<CategoriaDto> categorias(){
		List<Categoria> categorias = categoriaRepository.findAll();
				
		return CategoriaDto.converter(categorias);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {
		Categoria categoria = form.converter();
		categoriaRepository.save(categoria);
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
}
