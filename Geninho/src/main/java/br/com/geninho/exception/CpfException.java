package br.com.geninho.exception;

public class CpfException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfException(String message) {
		super(message);
	}
}
