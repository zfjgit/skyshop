/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.exception.handler;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sitv.skyshop.common.exception.ParamValidException;
import com.sitv.skyshop.common.exception.PlainReponseException;
import com.sitv.skyshop.dto.ResponseInfo;

/**
 * @author zfj20 @ 2017年11月16日
 */
@ControllerAdvice
public class ChairGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(ChairGlobalExceptionHandler.class);

	@ExceptionHandler(PlainReponseException.class)
	private ResponseEntity<String> plainResponseExceptionHandler(PlainReponseException e) {
		log.error("---------> PlainReponseException!!!", e);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<>("code=0", headers, HttpStatus.OK);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	private <T> ResponseInfo<T> runtimeExceptionHandler(Exception e) {
		log.error("---------> RUNTIME_ERROR!!!", e);
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			return ResponseInfo.NOT_FOUND_ERROR(e.getMessage());
		}
		return ResponseInfo.RUNTIME_ERROR(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	private <T> ResponseInfo<T> illegalParamsExceptionHandler(ValidationException e) {
		log.error("---------> ARGS_ERROR!!!", e);
		return ResponseInfo.ARGS_ERROR("参数错误");
	}

	@ResponseBody
	@ExceptionHandler(ParamValidException.class)
	private <T> ResponseInfo<T> illegalParamsExceptionHandler(ParamValidException e) {
		log.error("---------> ARGS_ERROR!!!", e);
		return ResponseInfo.ARGS_ERROR("参数错误");
	}

}
