package br.com.compasso.lambda.desafioCompasso.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.AtualizacaoTopicoForm;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoCategoriaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeForm;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.exception.ObjetoIsNull;
import br.com.compasso.lambda.desafioCompasso.models.Categoria;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.repositories.FilmeRepository;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository filmeRepository;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private CategoriaService categoriaService;

	// private List<Filme> filmes = new ArrayList<Filme>();

	// Métodos
	//
	@Transactional(readOnly = true)
	public List<Filme> getFilmes() {
		List<Filme> filmes = filmeRepository.findAll();
		return filmes;
	}

	// ok
	public ResponseEntity<FilmeCompletoDto> postFilme(@Valid FilmeForm form, UriComponentsBuilder uriBuilder) {
		Filme filme = form.converter();
		List<Filme> filmes = getFilmes();
		HttpStatus status;
		if (filmes.contains(filme)) {
			System.out.println("Tentou Adicionar Filme Repetido");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		filmeRepository.save(filme);

		URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filme.getId()).toUri();
		return ResponseEntity.created(uri).body(new FilmeCompletoDto(filme));

	}

	public ResponseEntity<FilmeCompletoDto> update(Long id, @Valid FilmeForm form) {
		Optional<Filme> optional = filmeRepository.findById(id);
		if (optional.isPresent()) {
			optional.get().setNome(form.getNome());
			optional.get().setDescricao(form.getDescricao());
			optional.get().setComentario(form.getComentario());
			optional.get().setDiretor(form.getDiretor());
			optional.get().setElenco(form.getElenco());
			optional.get().setEstudio(form.getEstudio());
			Filme filmeAtualizado = filmeRepository.save(optional.get());
			return ResponseEntity.ok(new FilmeCompletoDto(filmeAtualizado));
		}

		return ResponseEntity.notFound().build();
	}

	public void delete(Long id) {
		filmeRepository.deleteById(id);

	}

	public ResponseEntity<FilmeCompletoDto> deleteFilmeDoMyList(Long idFilme, Long idPessoa,
			UriComponentsBuilder uriBuilder) {
		Optional<Filme> filme = getFilmeById(idFilme);
		Pessoa pessoa = pessoaService.getById(idPessoa);
		filme.get().getPessoas().contains(pessoa);
		filme.get().getPessoas().remove(pessoa);
		salvar(filme.get());
		URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filme.get().getId()).toUri();
		return ResponseEntity.ok(new FilmeCompletoDto(filme.get()));
	}

	public Optional<Filme> getFilmeById(Long id) {
		return filmeRepository.findById(id);
	}

	public void salvar(Filme filme) {

		filmeRepository.save(filme);
	}

	public ResponseEntity<FilmeCompletoCategoriaDto> associaCategoria(Long idCategoria, Long idFilme,
			UriComponentsBuilder uriBuilder) {
		Categoria categoria = categoriaService.getById(idCategoria);

		Optional<Filme> filme = getFilmeById(idFilme);

		if (filme.isPresent() && !filme.get().getCategorias().contains(categoria)) {

			filme.get().getCategorias().add(categoria);
			salvar(filme.get());

			URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filme.get().getId()).toUri();
			return ResponseEntity.created(uri).body(new FilmeCompletoCategoriaDto(filme.get()));
		}
		return ResponseEntity.badRequest().build();

	}
//	public void imprimeById(int idFilme) {
//		for (Filme filme : filmes) {
//			if (filme.getId() == idFilme) {
//				System.out.println(filme);
//			}
//		}
//	}
//
//	public boolean imprimeByName(String nomeFilme) {
//		for (Filme f : filmes) {
//			if (f.getNome() == nomeFilme.toUpperCase()) {
//				return true;
//			}
//		}
//
//		System.out.println("Filme não encontrado");
//		return false;
//	}
//
//	public boolean removeById(int idFilme) {
//		for (Filme f : filmes) {
//			if (f.getId() == idFilme) {
//				filmes.remove(f);
//				return true;
//			}
//
//		}
//		System.out.println("Id não encontrado");
//		return false;
//	}
//
//	public void adiciona(String descricao, String nome) {
//		Filme filme = new Filme();
//
//		if(filmes.contains(filme)) {
//			System.out.println("Filme já existe!");
//		} else {
//			filmes.add(filme);
//			System.out.println("Filme adicionado com sucesso!");
//		}
//
//	}
//
//	public List<Filme> imprimeTodos() {
//		return filmes;
//	}
//
//	public boolean verificaById(int id) {
//		for (Filme filme : filmes) {
//			if (filme.getId() == id) {
//				return true;
//			}
//		}
//
//		System.out.println("Filme com esse id já existe!");
//		return false;
//
//	}

}
