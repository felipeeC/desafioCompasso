package br.com.compasso.lambda.desafioCompasso.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.lambda.desafioCompasso.models.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Optional<Pessoa> findById(Long id);
	
}
