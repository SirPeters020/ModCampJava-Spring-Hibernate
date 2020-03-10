package br.com.lifetime.services.exception;

/**
 * 
 * Classe auxiliar para tratamento de Data Integrity Exception
 *
 */
public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityException (String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	
}
