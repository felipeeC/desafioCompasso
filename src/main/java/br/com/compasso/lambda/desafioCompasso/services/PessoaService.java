package br.com.compasso.lambda.desafioCompasso.services;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaAtualizaForm;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaForm;
import br.com.compasso.lambda.desafioCompasso.exception.ObjectNotFoundException;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	// Métodoss

	@Transactional(readOnly = true)
	public Page<PessoaDto> retornaTodas(Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
		return PessoaDto.converter(pessoas);
	}

	@Transactional(readOnly = true)
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}

	public PessoaDto cadastrarPessoa(@Valid PessoaForm form) {

		Pessoa pessoa = form.converter();
		List<Pessoa> pessoas = pessoaRepository.findAll();

		if (pessoas.contains(pessoa)) {
			return null;
		}

		pessoaRepository.save(pessoa);

		return new PessoaDto(pessoa);
	}

	public PessoaDto atualizarPessoa(Long id, @Valid PessoaAtualizaForm form) {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if (optional.isPresent()) {
			optional.get().setNome(form.getNome());
			optional.get().setAniversario(form.getAniversario());
			optional.get().setEmail(form.getEmail());
			Pessoa pessoaAtualizada = pessoaRepository.save(optional.get());
			return new PessoaDto(pessoaAtualizada);
		}

		return null;
	}

	public Pessoa getById(Long id) {
		return pessoaRepository.findById(id).get();
	}
	
	public Pessoa findPessoaById(Long id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class));
	}

	public Boolean deleteById(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);

		if (pessoa.isPresent()) {
			pessoaRepository.delete(pessoa.get());
			return true;
		}

		return false;
	}

}
