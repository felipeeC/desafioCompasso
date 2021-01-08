package br.com.compasso.lambda.desafioCompasso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.repositories.PessoaRepository;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<PessoaDto> todasPessoas(){
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		return PessoaDto.converter(pessoas);
	}

}
