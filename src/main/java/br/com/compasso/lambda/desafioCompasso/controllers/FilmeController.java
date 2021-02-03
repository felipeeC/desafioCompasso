package br.com.compasso.lambda.desafioCompasso.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.LastModified;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoCategoriaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeForm;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.services.CategoriaService;
import br.com.compasso.lambda.desafioCompasso.services.FilmeService;
import br.com.compasso.lambda.desafioCompasso.services.PessoaService;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {

	@Autowired
	private FilmeService filmeService;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public List<FilmeDto> filmes() {
		List<Filme> filmes = filmeService.getFilmes();
		return FilmeDto.converter(filmes);
	}

	// ok
	@PostMapping("/{idfilme}/associar-pessoa/{idpessoa}")
	public ResponseEntity<FilmeCompletoDto> associarPessoa(@PathVariable(name = "idpessoa") long idPessoa,
			@PathVariable(name = "idfilme") long idFilme, UriComponentsBuilder uriBuilder) {
		Filme filmeAssociado = filmeService.associaPessoa(idFilme, idFilme);
		if (filmeAssociado != null) {

			URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filmeAssociado.getId()).toUri();
			return ResponseEntity.created(uri).body(new FilmeCompletoDto(filmeAssociado));

		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	// ok
	@PostMapping("/{idfilme}/associar-categoria/{idcategoria}")
	public ResponseEntity<FilmeCompletoCategoriaDto> associarCategoria(
			@PathVariable(name = "idcategoria") long idCategoria, @PathVariable(name = "idfilme") long idFilme,
			UriComponentsBuilder uriBuilder) {

		if (filmeService.associaCategoria(idCategoria, idFilme)) {
			Optional<Filme> filme = filmeService.getFilmeById(idFilme);
			URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filme.get().getId()).toUri();
			return ResponseEntity.created(uri).body(new FilmeCompletoCategoriaDto(filme.get()));
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	// ok
	@GetMapping(value = "/mylist/{idpessoa}")
	public List<FilmeDto> filmesPessoa(@PathVariable(name = "idpessoa") long idPessoa) {
		List<FilmeDto> filmes = filmeService.getMinhaListaDeFilmes(idPessoa);

		if (filmes != null) {
			return filmes;
		} else {
			return (List<FilmeDto>) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// ok
	@DeleteMapping(value = "mylist/{idpessoa}/delete/{idfilme}")
	public ResponseEntity<FilmeCompletoDto> removeFilmeMyList(@PathVariable(name = "idfilme") Long idFilme,
			@PathVariable(name = "idpessoa") long idPessoa, UriComponentsBuilder uriBuilder) {
		Optional<Filme> filme = filmeService.getFilmeById(idFilme);
		if (filmeService.deleteFilmeDoMyList(idFilme, idPessoa)) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	// ok
	@GetMapping(value = "/completo")
	public List<FilmeCompletoDto> filmesCompletos() {
		List<Filme> filmes = filmeService.getFilmes();

		return FilmeCompletoDto.converter(filmes);
	}

	// ok
	@PostMapping
	public ResponseEntity<FilmeCompletoDto> cadastrar(@RequestBody @Valid FilmeForm form,
			UriComponentsBuilder uriBuilder) {
		Filme filmeNovo = filmeService.postFilme(form);
		if (filmeNovo != null) {
			URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filmeNovo.getId()).toUri();
			return ResponseEntity.created(uri).body(new FilmeCompletoDto(filmeNovo));
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Void> atualizarFilme(@PathVariable Long id, @RequestBody @Valid FilmeForm form) {
		Filme obj = form.converter();
		obj.setId(id);
		obj = filmeService.update(obj);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		filmeService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
