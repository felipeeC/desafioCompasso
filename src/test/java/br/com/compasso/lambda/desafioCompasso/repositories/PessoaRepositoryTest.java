package br.com.compasso.lambda.desafioCompasso.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.compasso.lambda.desafioCompasso.models.Pessoa;

@DataJpaTest

public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository repository;

	// OK
	@Test
	public void retornaIdExiste() {
		Long id = 1L;
		Optional<Pessoa> findById = repository.findById(id);

		assertEquals(id, findById.get().getId());
	}

	// OK
	@Test
	public void retornaIdNaoExiste() {
		Long id = 5L;
		Optional<Pessoa> findById = repository.findById(id);

		assertTrue(findById.isEmpty());
	}
}
