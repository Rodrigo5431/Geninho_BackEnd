package br.com.geninho.exception;

public class IncorrectCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectCodeException(String message) {
		super(message);
	}
}
