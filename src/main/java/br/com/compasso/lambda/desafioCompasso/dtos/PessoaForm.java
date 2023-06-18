package br.com.compasso.lambda.desafioCompasso.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.compasso.lambda.desafioCompasso.models.Pessoa;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PessoaForm {

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 60)
	private String nome;

	@NotNull
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String aniversario;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String senha;

	public Pessoa converter() {
		return new Pessoa(nome, LocalDate.parse(aniversario), email, senha);
	}

	public String getNome() {
		return nome;
	}

	public String getAniversario() {
		return aniversario;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAniversario(String aniversario) {
		this.aniversario = aniversario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
