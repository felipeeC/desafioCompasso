package br.com.compasso.lambda.desafioCompasso.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;

public class CategoriaDto {

		private Long id;
		private String nome;

		public CategoriaDto(Categoria categoria) {
			this.id = categoria.getId();
			this.nome = categoria.getNome();
		}

		public Long getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}

		public static List<CategoriaDto> converter(List<Categoria> categorias) {
			return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
		}
	}
