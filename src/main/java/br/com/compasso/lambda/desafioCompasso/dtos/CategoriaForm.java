package br.com.compasso.lambda.desafioCompasso.dtos;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;

public class CategoriaForm {
	
		private String nome;


		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Categoria converter() {

			return new Categoria(nome);
		}
	}
