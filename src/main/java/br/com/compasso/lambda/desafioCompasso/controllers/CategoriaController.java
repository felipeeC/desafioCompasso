package br.com.compasso.lambda.desafioCompasso.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.CategoriaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.CategoriaForm;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeDto;
import br.com.compasso.lambda.desafioCompasso.models.Categoria;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public List<CategoriaDto> categorias() {
		List<Categoria> categorias = categoriaService.getCategorias();
		return CategoriaDto.converter(categorias);
	}

	@GetMapping(value = "/filmes/{idcategoria}")
	public List<FilmeDto> filmesCategoria(@PathVariable(name = "idcategoria") long idCategoria) {
		Categoria categoria = categoriaService.getById(idCategoria);
		List<Filme> filmes = categoria.getFilmes();
		return FilmeDto.converter(filmes);
	}

	@PostMapping
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form,
			UriComponentsBuilder uriBuilder) {
		Categoria categoria = categoriaService.postCategoria(form);

		if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

		} else {
			URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
			return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
