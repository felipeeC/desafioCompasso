package br.com.compasso.lambda.desafioCompasso.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoCategoriaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeForm;
import br.com.compasso.lambda.desafioCompasso.models.Categoria;
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

	@PostMapping("/{idfilme}/associar-pessoa/{idpessoa}")
	public ResponseEntity<FilmeCompletoDto> associarPessoa(@PathVariable(name = "idpessoa") long idPessoa,
			@PathVariable(name = "idfilme") long idFilme, UriComponentsBuilder uriBuilder) {
		Pessoa pessoa = pessoaService.getById(idPessoa);
		Optional<Filme> filme = filmeService.getFilmeById(idFilme);
		List<Pessoa> pessoas = filme.get().getPessoas();

		if (filme.isPresent() && !filme.get().getPessoas().contains(pessoa)) {
			filme.get().getPessoas().add(pessoa);
			filmeService.salvar(filme.get());

			URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filme.get().getId()).toUri();
			return ResponseEntity.created(uri).body(new FilmeCompletoDto(filme.get()));
		}
		return ResponseEntity.badRequest().build();
	}
	//ok
	@PostMapping("/{idfilme}/associar-categoria/{idcategoria}")
	public ResponseEntity<FilmeCompletoCategoriaDto> associarCategoria(
			@PathVariable(name = "idcategoria") long idCategoria, @PathVariable(name = "idfilme") long idFilme,
			UriComponentsBuilder uriBuilder) {
		return filmeService.associaCategoria(idCategoria, idFilme, uriBuilder);
	}
	
	//ok
	@GetMapping(value = "/mylist/{idpessoa}")
	public List<FilmeDto> filmesPessoa(@PathVariable(name = "idpessoa") long idPessoa) {
		Pessoa pessoa = pessoaService.getById(idPessoa);
		List<Filme> filmes = pessoa.getFilmes();
		return FilmeDto.converter(filmes);
	}
	
	//ok
	@RequestMapping(value = "mylist/{idpessoa}/delete/{idfilme}", method = RequestMethod.DELETE)
	public ResponseEntity<FilmeCompletoDto> removeFilmeMyList(@PathVariable(name = "idfilme") Long idFilme,
			@PathVariable(name = "idpessoa") long idPessoa, UriComponentsBuilder uriBuilder) {
		
		return filmeService.deleteFilmeDoMyList(idFilme, idPessoa, uriBuilder);
	}

	//ok
	@GetMapping(value = "/completo")
	public List<FilmeCompletoDto> filmesCompletos() {
		List<Filme> filmes = filmeService.getFilmes();

		return FilmeCompletoDto.converter(filmes);
	}

	//ok
	@PostMapping
	public ResponseEntity<FilmeCompletoDto> cadastrar(@RequestBody @Valid FilmeForm form,
			UriComponentsBuilder uriBuilder) {
		
		return filmeService.postFilme(form, uriBuilder);
	}
	
	//ok
	@PutMapping(value = "/completo/{id}")
	public ResponseEntity<FilmeCompletoDto> atualizarFilme(@PathVariable Long id, @RequestBody @Valid FilmeForm form) {
		return filmeService.update(id, form);

	}

	//ok
	@DeleteMapping(value = "/completo/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		filmeService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
