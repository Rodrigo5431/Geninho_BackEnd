package br.com.geninho.exception;

public class PasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordException(String message) {
		super(message);
	}
}
