package br.com.compasso.lambda.desafioCompasso.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;

public class CategoriaDto {

		private long id;
		private String nome;

		public CategoriaDto(Categoria categoria) {
			this.id = categoria.getId();
			this.nome = categoria.getNome();
		}

		public long getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}

		public static Page<CategoriaDto> converter(Page<Categoria> categorias) {
			return categorias.map(CategoriaDto :: new);
		}
	}
