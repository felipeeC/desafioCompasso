package br.com.compasso.lambda.desafioCompasso.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaAtualizaForm;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaForm;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PessoaServiceTest {

	@Autowired
	private PessoaService pessoaService;

	// Ok
	@Test
	public void retornaTodasPessoas() {
		Pageable paginacao = PageRequest.of(0, 10);
		Page<PessoaDto> pessoas = pessoaService.retornaTodas(paginacao);

		Pessoa pessoa1 = new Pessoa("Guilherme", LocalDate.now(), "teste1");
		Pessoa pessoa2 = new Pessoa("Rafael", LocalDate.now(), "teste2");
		Pessoa pessoa3 = new Pessoa("Gabriel", LocalDate.now(), "teste3");

		assertEquals(pessoa1.getNome(), pessoas.toList().get(0).getNome());
		assertEquals(pessoa2.getNome(), pessoas.toList().get(1).getNome());
		assertEquals(pessoa3.getNome(), pessoas.toList().get(2).getNome());
	}

	// Ok
	@Test
	public void retornaPessoaPorId() {
		Optional<Pessoa> pessoa = pessoaService.findById(1L);

		Pessoa pessoa1 = new Pessoa("Guilherme", LocalDate.now(), "teste1");

		assertEquals(pessoa1.getNome(), pessoa.get().getNome());
	}

	// Ok
	@Test
	public void retornaPessoaQueNaoExistePorId() {
		Optional<Pessoa> pessoa = pessoaService.findById(4L);

		Pessoa pessoa1 = new Pessoa();

		assertTrue(pessoa.isEmpty());
	}

	// Ok
	@Test
	public void CadastraPessoa() {
		PessoaForm form = new PessoaForm();
		form.setNome("Augusto");
		form.setAniversario("1994-12-12");
		form.setEmail("testealeatorio");

		pessoaService.cadastrarPessoa(form);

		Optional<Pessoa> pessoa = pessoaService.findById(4L);

		assertEquals("Augusto", pessoa.get().getNome());
	}

	// Ok
	@Test
	public void CadastraPessoaRepetida() {
		PessoaForm form = new PessoaForm();
		form.setNome("Guilherme");
		form.setAniversario("1994-12-12");
		form.setEmail("testealeatorio");

		pessoaService.cadastrarPessoa(form);

		Optional<Pessoa> pessoa = pessoaService.findById(4L);

		assertNotEquals("Guilherme", pessoa.get().getNome());
	}

	// Ok
	@Test
	public void CadastraPessoaComInformacaoFaltando() {
		PessoaForm form = new PessoaForm();
		form.setNome("");
		form.setAniversario("1994-12-12");
		form.setEmail("testealeatorio");

		pessoaService.cadastrarPessoa(form);

		Optional<Pessoa> pessoa = pessoaService.findById(4L);

		assertNotEquals("", pessoa.get().getNome());
	}

	// Ok
	@Test
	public void AtualizaPessoa() {
		PessoaAtualizaForm form = new PessoaAtualizaForm();
		form.setNome("Novo");
		form.setAniversario(LocalDate.now());
		form.setEmail("testealeatorio");

		pessoaService.atualizarPessoa(1L, form);

		Optional<Pessoa> pessoa = pessoaService.findById(1L);

		assertEquals("Novo", pessoa.get().getNome());
	}

	// Not Ok
	@Test
	public void AtualizaPessoaSemInformacao() {
		PessoaAtualizaForm form = new PessoaAtualizaForm();
		form.setNome("");
		form.setAniversario(LocalDate.now());
		form.setEmail("");

		pessoaService.atualizarPessoa(1L, form);

		Optional<Pessoa> pessoa = pessoaService.findById(1L);

		assertEquals("Guilherme", pessoa.get().getNome());
	}

}
