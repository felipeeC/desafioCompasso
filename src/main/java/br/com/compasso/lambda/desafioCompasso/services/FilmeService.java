package br.com.compasso.lambda.desafioCompasso.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoDto;
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
	//
	@Transactional(readOnly = true)
	public List<Filme> getFilmes() {
		List<Filme> filmes = filmeRepository.findAll();
		return filmes;
	}

	// ok
	public Filme postFilme(@Valid FilmeForm form) {
		Filme filme = converteFilmeForm(form);
		List<Filme> filmes = getFilmes();
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

	public boolean deleteFilmeDoMyList(Long idFilme, Long idPessoa) {
		Filme filme = find(idFilme);
		if(filme != null) {
			Pessoa pessoa = pessoaService.getById(idPessoa);
			if (filme.getPessoas().contains(pessoa)) {
				filme.getPessoas().remove(pessoa);
				salvar(filme);
				return true;
				}else {return false;
				}
		}
		 else {
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

	public List<FilmeDto> getMinhaListaDeFilmes(Long idPessoa) {
		Pessoa pessoa = pessoaService.findPessoaById(idPessoa);
		
		List<Filme> filmes = pessoa.getFilmes();
		return FilmeDto.converter(filmes);
		
		
	}

}
