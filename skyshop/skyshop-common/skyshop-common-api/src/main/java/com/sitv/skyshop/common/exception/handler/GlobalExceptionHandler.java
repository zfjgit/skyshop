package com.sitv.skyshop.common.exception.handler;

import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sitv.skyshop.common.exception.EntityNotFoundException;
import com.sitv.skyshop.common.exception.EntityStatusException;
import com.sitv.skyshop.common.exception.OperateResultReceivException;
import com.sitv.skyshop.common.exception.OrderExpiredException;
import com.sitv.skyshop.common.exception.OrderNotPaidException;
import com.sitv.skyshop.common.exception.ParamValidException;
import com.sitv.skyshop.common.exception.PlainReponseException;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.exception.CheckCodeVerificationFailedException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj 2017-12-11
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("发生错误：" + ex.getMessage(), ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@ResponseBody
	@ExceptionHandler(CheckCodeVerificationFailedException.class)
	private <T> ResponseInfo<T> entityVarificationFailedExceptionHandler(CheckCodeVerificationFailedException e) {
		log.error("---------> 实体对象CHECKCODE校验错误!!!", e);
		return ResponseInfo.CHECKCODE_VARIFY_ERROR(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(OrderNotPaidException.class)
	private <T> ResponseInfo<T> orderNotPaidExceptionHandler(OrderNotPaidException e) {
		log.error("---------> 订单未支付!!!", e);
		return ResponseInfo.ORDER_NOTPAID_ERROR(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(OrderExpiredException.class)
	private <T> ResponseInfo<T> orderExpiredExceptionHandler(OrderExpiredException e) {
		log.error("---------> 订单已过期!!!", e);
		return ResponseInfo.ORDER_EXPIRED_ERROR(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(UnsupportedOperationException.class)
	private <T> ResponseInfo<T> unsupportedOperationExceptionHandler(UnsupportedOperationException e) {
		log.error("---------> 不允许的操作!!!", e);
		return ResponseInfo.FORBIDDEN_ERROR(e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(EntityStatusException.class)
	private <T> ResponseInfo<T> entityStatusExceptionHandler(EntityStatusException e) {
		log.error("---------> 实体对象状态错误!!!", e);
		return ResponseInfo.UNNORMAL_STATUS("实体对象状态错误");
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

	@ExceptionHandler(OperateResultReceivException.class)
	private ResponseEntity<String> operateResultReceivExceptionHandler(OperateResultReceivException e) {
		log.error("---------> OperateResultReceivException!!!", e);
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
	private <T> ResponseInfo<T> validationExceptionHandler(ValidationException e) {
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
