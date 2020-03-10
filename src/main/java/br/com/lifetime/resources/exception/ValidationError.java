package br.com.lifetime.resources.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe auxiliar para tratamento de erro de Validação de dados
 *
 */
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStramp) {
		super(status, msg, timeStramp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	
	

	
	
	
}
