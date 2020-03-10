package br.com.lifetime.services.exception;


/**
 * 
 * @author pedro.silva
 * Classe para tratar Constraint Violation Exception
 *
 */
public class ConstraintViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ConstraintViolationException (String msg) {
		super(msg);
	}
	
	public ConstraintViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	

}
