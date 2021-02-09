package br.com.compasso.lambda.desafioCompasso.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.CategoriaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoCategoriaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeForm;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
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
	public ResponseEntity<Page<FilmeDto>> todosFilmes(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		Page<Filme> filmes = filmeService.getFilmes(paginacao);
		return ResponseEntity.ok(FilmeDto.converterPage(filmes));

	}

	@GetMapping("/{idfilme}")
	public ResponseEntity<FilmeCompletoDto> findFilmeById(@PathVariable(name = "idfilme") long idFilme) {

		Filme filme = filmeService.find(idFilme);
		return ResponseEntity.ok(new FilmeCompletoDto(filme));

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

	@DeleteMapping(value = "/{idfilme}/desassociar-categoria/{idcategoria}")
	public ResponseEntity<CategoriaDto> desassociarCategoria(@PathVariable(name = "idfilme") Long idFilme,
			@PathVariable(name = "idcategoria") long idCategoria, UriComponentsBuilder uriBuilder) {
		Optional<Filme> filme = filmeService.getFilmeById(idFilme);
		if (filmeService.desassociaCategoria(idFilme, idCategoria)) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
	@DeleteMapping(value = "/{idfilme}/desassociar-pessoa/{idpessoa}")
	public ResponseEntity<FilmeCompletoDto> desassociarPessoa(@PathVariable(name = "idfilme") Long idFilme,
			@PathVariable(name = "idpessoa") long idPessoa, UriComponentsBuilder uriBuilder) {
		Optional<Filme> filme = filmeService.getFilmeById(idFilme);
		if (filmeService.desassociaPessoa(idFilme, idPessoa)) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	// ok
	@GetMapping(value = "/completo")
	public Page<FilmeCompletoDto> filmesCompletos(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Filme> filmes = filmeService.getFilmes(paginacao);

		return FilmeCompletoDto.converterPage(filmes);
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

	@PatchMapping(value = "/{id}/avaliacao")
	public ResponseEntity<Void> avaliaFilme(@PathVariable Long id, @RequestBody @Valid FilmeForm form) {
		Filme filme = form.converter();
		filme.setId(id);
		filme = filmeService.avaliaFilme(filme);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		filmeService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
