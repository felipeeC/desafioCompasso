package br.com.compasso.lambda.desafioCompasso.exception;

public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflictException(String msg) {
		super(msg);
	}
	
	public ConflictException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
