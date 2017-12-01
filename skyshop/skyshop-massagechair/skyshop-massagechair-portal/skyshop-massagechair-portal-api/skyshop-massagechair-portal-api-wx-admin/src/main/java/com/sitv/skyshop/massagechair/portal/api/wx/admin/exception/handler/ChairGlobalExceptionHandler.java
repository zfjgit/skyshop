/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.exception.handler;

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

import com.sitv.skyshop.common.exception.EntityNotFoundException;
import com.sitv.skyshop.common.exception.EntityStatusException;
import com.sitv.skyshop.common.exception.ParamValidException;
import com.sitv.skyshop.common.exception.PlainReponseException;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.exception.CheckCodeVerificationFailedException;
import com.sitv.skyshop.massagechair.dto.MassageChairResponseInfo;

/**
 * @author zfj20 @ 2017年11月16日
 */
@ControllerAdvice
public class ChairGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(ChairGlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(CheckCodeVerificationFailedException.class)
	private <T> ResponseInfo<T> entityVarificationFailedExceptionHandler(CheckCodeVerificationFailedException e) {
		log.error("---------> 实体对象CHECKCODE校验错误!!!", e);
		return ResponseInfo.CHECKCODE_VARIFY_ERROR(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(EntityStatusException.class)
	private <T> MassageChairResponseInfo<T> entityStatusExceptionHandler(EntityStatusException e) {
		log.error("---------> 实体对象状态错误!!!", e);
		return MassageChairResponseInfo.UNNORMAL_STATUS("实体对象状态错误");
	}

	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	private <T> ResponseInfo<T> entityNotFoundExceptionHandler(EntityNotFoundException e) {
		log.error("---------> 实体对象不存在!!!", e);
		return ResponseInfo.NOT_FOUND_ERROR("实体对象不存在");
	}

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
