/**
 *
 */
package com.sitv.skyshop.common.exception;

import java.util.List;

import org.springframework.validation.FieldError;

/**
 * @author zfj20 @ 2017年11月16日
 */
public class ParamValidException extends Exception {

	private static final long serialVersionUID = -570429469575395681L;

	private List<FieldError> errors;

	public ParamValidException(List<FieldError> errors) {
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
}
