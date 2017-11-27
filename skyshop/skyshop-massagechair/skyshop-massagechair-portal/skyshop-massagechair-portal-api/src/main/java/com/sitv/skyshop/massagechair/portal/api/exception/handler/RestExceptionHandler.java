/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.exception.handler;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sitv.skyshop.common.exception.ParamValidException;
import com.sitv.skyshop.dto.ResponseInfo;

/**
 * @author zfj20 @ 2017年11月16日
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler
	private <T> ResponseInfo<T> runtimeExceptionHandler(Exception e) {
		log.error("---------> RUNTIME_ERROR!!!", e);
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			return ResponseInfo.NOT_FOUND_ERROR(e.getMessage());
		}
		return ResponseInfo.RUNTIME_ERROR(e.getMessage());
	}

	@ExceptionHandler(ValidationException.class)
	private <T> ResponseInfo<T> illegalParamsExceptionHandler(ValidationException e) {
		log.error("---------> ARGS_ERROR!!!", e);
		return ResponseInfo.ARGS_ERROR("参数错误");
	}

	@ExceptionHandler(ParamValidException.class)
	private <T> ResponseInfo<T> illegalParamsExceptionHandler(ParamValidException e) {
		log.error("---------> ARGS_ERROR!!!", e);
		return ResponseInfo.ARGS_ERROR("参数错误");
	}

}
