package com.sitv.skyshop.common.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitv.skyshop.common.dto.CommonResponseInfo;
import com.sitv.skyshop.dto.ResponseInfo;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = Throwable.class)
	public ResponseInfo<String> defaultHandler(HttpServletRequest request, Throwable e) {
		log.error(e.getMessage(), e);

		ResponseInfo<String> info = CommonResponseInfo.RUNTIME_ERROR(e.getMessage());
		info.setUrl(request.getRequestURL().toString());
		info.setData("ERROR STACKS：" + ExceptionUtils.getStackTrace(e));
		return info;
	}
}
