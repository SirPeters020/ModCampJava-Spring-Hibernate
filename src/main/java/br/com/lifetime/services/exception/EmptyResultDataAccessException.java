package br.com.lifetime.services.exception;

/**
 * 
 * Classe auxiliar para tratamento de Empty Result Data Access Exception
 *
 */
public class EmptyResultDataAccessException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EmptyResultDataAccessException (String msg) {
		super(msg);
	}
	
	public EmptyResultDataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
