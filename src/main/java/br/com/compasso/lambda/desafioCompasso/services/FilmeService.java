package br.com.compasso.lambda.desafioCompasso.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeDto;
import br.com.compasso.lambda.desafioCompasso.dtos.FilmeForm;
import br.com.compasso.lambda.desafioCompasso.exception.ObjectNotFoundException;
import br.com.compasso.lambda.desafioCompasso.models.Categoria;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.repositories.FilmeRepository;

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
	
	@Transactional(readOnly = true)
	public Page<Filme> getFilmes(Pageable paginacao) {
		Page<Filme> filmes = filmeRepository.findAll(paginacao);
		return filmes;
	}

	// ok
	public Filme postFilme(@Valid FilmeForm form) {
		Filme filme = converteFilmeForm(form);
		List<Filme> filmes = filmeRepository.findAll();

		if (filmes.contains(filme)) {
			System.out.println("Tentou Adicionar Filme Repetido");
			return null;
		}
		filmeRepository.save(filme);
		return filme;
	}

	public Filme converteFilmeForm(FilmeForm form) {
		Filme filme = form.converter();
		return filme;
	}

	public Filme update(Filme obj) {
		Filme newObj = find(obj.getId());
		updateData(newObj, obj);
		return filmeRepository.save(newObj);
	}

	public void updateData(Filme newObj, Filme obj) {
		newObj.setNome(obj.getNome());
		newObj.setDescricao(obj.getDescricao());
		newObj.setComentario(obj.getComentario());
		newObj.setDiretor(obj.getDiretor());
		newObj.setEstudio(obj.getEstudio());
		newObj.setElenco(obj.getElenco());
	}
	
	public Filme avaliaFilme(Filme obj) {
		Filme filme = find(obj.getId());
		filme.setAvaliacao(obj.getAvaliacao());
		return filmeRepository.save(filme);
	}

	public void delete(Long id) {
		find(id);
		try {
			filmeRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não é possível excluir um filme porque há entidades relacionadas");
		}

	}

	public Filme find(Long id) {
		Optional<Filme> obj = filmeRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Filme.class));
	}

	public boolean desassociaPessoa(Long idFilme, Long idPessoa) {
		Filme filme = find(idFilme);
		if (filme != null) {
			Pessoa pessoa = pessoaService.getById(idPessoa);
			if (filme.getPessoas().contains(pessoa)) {
				filme.getPessoas().remove(pessoa);
				salvar(filme);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public Optional<Filme> getFilmeById(Long id) {
		return filmeRepository.findById(id);
	}

	public void salvar(Filme filme) {
		filmeRepository.save(filme);
	}

	public boolean associaCategoria(Long idCategoria, Long idFilme) {
		Categoria categoria = categoriaService.getById(idCategoria);

		Optional<Filme> filme = getFilmeById(idFilme);

		if (filme.isPresent() && !filme.get().getCategorias().contains(categoria)) {

			filme.get().getCategorias().add(categoria);
			salvar(filme.get());
			return true;
		} else {
			return false;
		}

	}

	public List<FilmeDto> getMinhaListaDeFilmes(Long idPessoa) {
		Pessoa pessoa = pessoaService.findPessoaById(idPessoa);

		List<Filme> filmes = pessoa.getFilmes();
		return FilmeDto.converter(filmes);

	}

	public Filme associaPessoa(Long idPessoa, Long idFilme) {
		Pessoa pessoa = pessoaService.getById(idPessoa);

		Filme filme = find(idFilme);
		List<Pessoa> pessoas = filme.getPessoas();

		if (filme != null && !filme.getPessoas().contains(pessoa)) {
			filme.getPessoas().add(pessoa);
			salvar(filme);
			return filme;
		} else {
			return null;
		}

	}

	public boolean desassociaCategoria(Long idFilme, Long idCategoria) {
		Filme filme = find(idFilme);
		if (filme != null) {
			Categoria categoria = categoriaService.getById(idCategoria);
			if (filme.getCategorias().contains(categoria)) {
				filme.getCategorias().remove(categoria);
				salvar(filme);
				return true;
			} else {
				System.out.println("contains n funfa");
				return false;
			}
		} else {
			System.out.println("ta retornando null");
			return false;
		}

	}
}
