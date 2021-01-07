package br.com.compasso.lambda.desafioCompasso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.lambda.desafioCompasso.models.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
