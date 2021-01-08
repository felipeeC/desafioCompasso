package br.com.compasso.lambda.desafioCompasso.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

	private PessoaService pessoaService = new PessoaService();

	@GetMapping
	public List<PessoaDto> todasPessoas(){
		return pessoaService.retornaTodas();
	}

}
