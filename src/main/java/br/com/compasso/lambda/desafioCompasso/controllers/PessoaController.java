package br.com.compasso.lambda.desafioCompasso.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaAtualizaForm;
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
	public ResponseEntity<Page<PessoaDto>> todasPessoas(
			@PageableDefault(
					sort = "id", 
					direction = Direction.ASC, 
					page = 0, 
					size = 10) Pageable paginacao
			) {
		
		Page<PessoaDto> pessoas = pessoaService.retornaTodas(paginacao);
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> pessoaById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.findById(id);
		return ResponseEntity.ok(new PessoaDto(pessoa));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PessoaDto> cadastrarPessoa(
			@RequestBody @Valid PessoaForm form,
			UriComponentsBuilder uriBuilder
			) {
		PessoaDto novaPessoa = pessoaService.cadastrarPessoa(form);
		if(novaPessoa == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		URI uri = uriBuilder
				.path("/pessoas/{id}")
				.buildAndExpand(novaPessoa.getId())
				.toUri();

		return ResponseEntity.created(uri).body(novaPessoa);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaDto> atualizarPessoa(
			@PathVariable Long id,
			@RequestBody @Valid PessoaAtualizaForm form
			) {
		PessoaDto pessoaAtualizada = pessoaService.atualizarPessoa(id, form);
		if(pessoaAtualizada == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoaAtualizada);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(
			@PathVariable Long id
			){
		pessoaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}


