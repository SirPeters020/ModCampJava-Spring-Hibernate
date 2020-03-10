package br.com.lifetime.exceptions;

/**
 * 
 * Classe auxiliar para tratamento de Object Not Found Exception
 *
 */
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
