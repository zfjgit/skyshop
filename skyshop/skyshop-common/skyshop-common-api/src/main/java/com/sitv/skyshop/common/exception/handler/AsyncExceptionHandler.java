/**
 *
 */
package com.sitv.skyshop.common.exception.handler;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月20日
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	public void handleUncaughtException(Throwable t, Method m, Object... args) {
		log.error("AsyncExceptionHandler ------>method=" + m.getName());
		log.error("AsyncExceptionHandler ------>args=" + Arrays.toString(args));
		log.error(t.getMessage(), t);
	}

}
