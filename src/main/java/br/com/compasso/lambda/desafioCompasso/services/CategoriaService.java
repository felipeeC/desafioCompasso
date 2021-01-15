package br.com.compasso.lambda.desafioCompasso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;
import br.com.compasso.lambda.desafioCompasso.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional(readOnly = true)
	public List<Categoria> getCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
	
	public void cadastrarCategoria( Categoria categoria ) {
		categoriaRepository.save(categoria);
	}

	public void delete(long id) {
		categoriaRepository.deleteById(id);
	}

	public Categoria getById(long id) {
		return categoriaRepository.findById(id).get();
	}

	public Optional<Categoria> findByNome(String nome) {
		
		return categoriaRepository.findByNome(nome);
	}
}

