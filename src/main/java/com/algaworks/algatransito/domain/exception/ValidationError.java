package com.algaworks.algatransito.domain.exception;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public record ValidationError(
	String objectName,
	String field,
	String message
) {

	public ValidationError(ObjectError error) {
		this(
			error.getObjectName(), 
			((FieldError) error).getField(), 
			error.getDefaultMessage());
	}
	
}
	
