package br.com.compasso.lambda.desafioCompasso.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Optional<Pessoa> findById(Long id);
	
	

}
