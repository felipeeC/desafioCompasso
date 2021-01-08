package br.com.compasso.lambda.desafioCompasso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
