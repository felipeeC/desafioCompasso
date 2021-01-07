package br.com.compasso.lambda.desafioCompasso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.lambda.desafioCompasso.dtos.FilmeDto;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.repositories.FilmeRepository;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {

	@Autowired
	private FilmeRepository filmeRepository;
	
	@RequestMapping
	public List<FilmeDto> filmes(){
		List<Filme> filmes = filmeRepository.findAll();
				
		return FilmeDto.converter(filmes);
	}
	
	
	
}
