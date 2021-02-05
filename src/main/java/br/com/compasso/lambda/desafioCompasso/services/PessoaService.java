package br.com.compasso.lambda.desafioCompasso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaAtualizaForm;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaForm;
import br.com.compasso.lambda.desafioCompasso.exception.ConflictException;
import br.com.compasso.lambda.desafioCompasso.exception.ObjectNotFoundException;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private FilmeService filmeservice;

	@Transactional(readOnly = true)
	public Page<PessoaDto> retornaTodas(Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
		return PessoaDto.converter(pessoas);
	}

	@Transactional(readOnly = true)
	public Pessoa findById(Long id) {
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Objeto não encontrado! Id: ");
		stringBuilder.append(id);
		stringBuilder.append(", Tipo: ");
		stringBuilder.append(Pessoa.class);

		return pessoaEncontrada.orElseThrow(() -> new ObjectNotFoundException(stringBuilder.toString()));
	}

	public PessoaDto cadastrarPessoa(PessoaForm form) {

		Pessoa novaPessoa = form.converter();
		List<Pessoa> pessoas = pessoaRepository.findAll();

		if (pessoas.contains(novaPessoa)) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Pessoa com nome: ");
			stringBuilder.append(novaPessoa.getNome());
			stringBuilder.append(" e email: ");
			stringBuilder.append(novaPessoa.getEmail());
			stringBuilder.append(" já existe");

			throw new ConflictException(stringBuilder.toString());
		}

		pessoaRepository.save(novaPessoa);

		return new PessoaDto(novaPessoa);
	}

	public PessoaDto atualizarPessoa(Long id, PessoaAtualizaForm form) {
		Pessoa pessoaOriginal = findById(id);
		pessoaOriginal.setNome(form.getNome());
		pessoaOriginal.setAniversario(form.getAniversario());
		pessoaOriginal.setEmail(form.getEmail());

		Pessoa pessoaAtualizada = pessoaRepository.save(pessoaOriginal);
		return new PessoaDto(pessoaAtualizada);
	}

	public Pessoa getById(Long id) {
		return pessoaRepository.findById(id).get();
	}

	public Pessoa findPessoaById(Long id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class));
	}

	public void deleteById(Long id) {
		Pessoa pessoa = findById(id);
		desassociarFilmes(pessoa);
		pessoaRepository.delete(pessoa);
	}

	private void desassociarFilmes(Pessoa pessoa) {
		List<Filme> filmes = pessoa.getFilmes();
		for (Filme filme : filmes) {
			filmeservice.desassociaPessoa(filme.getId(), pessoa.getId());
		}
		
	}

	
}
