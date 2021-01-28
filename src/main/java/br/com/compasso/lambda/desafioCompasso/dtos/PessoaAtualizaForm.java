package br.com.compasso.lambda.desafioCompasso.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

public class PessoaAtualizaForm {

	@NotNull @NotEmpty @Length(min = 5, max = 60)
	private String nome;
	
	@Past
	private LocalDate aniversario;
	
	@Email
	private String email;

	public String getNome() {
		return nome;
	}

	public LocalDate getAniversario() {
		return aniversario;
	}

	public String getEmail() {
		return email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAniversario(LocalDate aniversario) {
		this.aniversario = aniversario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
