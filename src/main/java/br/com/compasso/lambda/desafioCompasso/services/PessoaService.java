package br.com.compasso.lambda.desafioCompasso.services;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaForm;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//Métodoss

	@Transactional(readOnly = true)
	public Page<PessoaDto> retornaTodas(Pageable paginacao){
		Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
		
		return PessoaDto.converter(pessoas);
	}

	@Transactional(readOnly = true)
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}

	public ResponseEntity<PessoaDto> cadastrarPessoa(
			@Valid PessoaForm form,
			UriComponentsBuilder uriBuilder) {
		Pessoa pessoa = form.converter();
		pessoaRepository.save(pessoa);
				
		URI uri = uriBuilder.path("/pessoas/{id}")
				.buildAndExpand(pessoa.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}


}
