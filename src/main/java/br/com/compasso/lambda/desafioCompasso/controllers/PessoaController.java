package br.com.compasso.lambda.desafioCompasso.controllers;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.AtualizacaoTopicoForm;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.dtos.PessoaForm;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public Page<PessoaDto> todasPessoas(
			@PageableDefault(
					sort = "id", 
					direction = Direction.ASC, 
					page = 0, 
					size = 10) Pageable paginacao
			) {
		return pessoaService.retornaTodas(paginacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> pessoaById(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.findById(id);
		
		if(pessoa.isPresent()) {
			return ResponseEntity.ok(new PessoaDto(pessoa.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PessoaDto> cadastrarPessoa(
			@RequestBody @Valid PessoaForm form,
			UriComponentsBuilder uriBuilder
			) {
		return pessoaService.cadastrarPessoa(form, uriBuilder);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaDto> atualizarPessoa(
			@PathVariable Long id,
			@RequestBody @Valid AtualizacaoTopicoForm form) { 
		return pessoaService.atualizarPessoa(id, form);
		
	}

}


