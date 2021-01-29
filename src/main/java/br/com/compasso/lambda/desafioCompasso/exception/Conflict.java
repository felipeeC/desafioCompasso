package br.com.compasso.lambda.desafioCompasso.exception;

public class Conflict extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public Conflict(String msg) {
		super(msg);
	}
	
	public Conflict(String msg, Throwable cause) {
		super(msg, cause);
	}

}
