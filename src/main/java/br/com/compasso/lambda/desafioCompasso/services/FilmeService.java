package br.com.compasso.lambda.desafioCompasso.services;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeCompletoDto;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.repositories.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository filmeRepository;

	// private List<Filme> filmes = new ArrayList<Filme>();

	// Métodos
	//
	@Transactional(readOnly = true)
	public List<Filme> getFilmes() {
		List<Filme> filmes = filmeRepository.findAll();
		return filmes;
	}

	public ResponseEntity<FilmeCompletoDto> postFilme(Filme filme, UriComponentsBuilder uriBuilder) {
		List<Filme> filmes = getFilmes();

		if (filmes.contains(filme)) {
			System.out.println("num deu");
			
			return ResponseEntity.notFound().build();
		} else {
			filmeRepository.save(filme);
			URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filme.getId()).toUri();
			return ResponseEntity.created(uri).body(new FilmeCompletoDto(filme));
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

}
