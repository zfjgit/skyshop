package com.sitv.skyshop.dao.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class DataSourceAdvice {

	private static final Logger log = LoggerFactory.getLogger(DataSourceAdvice.class);

	@Around(value = "execution(public * com.sitv.skyshop.*.dao.I*Dao.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();

		if (method.getName().startsWith("add") || method.getName().startsWith("create") || method.getName().startsWith("save") || method.getName().startsWith("edit")
				|| method.getName().startsWith("update") || method.getName().startsWith("delete") || method.getName().startsWith("remove") || method.getName().startsWith("auth")) {
			log.debug("WRITE DATASOURCE....");
			DataSourceSwitcher.setWrite();
		} else {
			log.debug("READ DATASOURCE....");
			DataSourceSwitcher.setRead();
		}

		Object r = null;
		try {
			r = joinPoint.proceed();
		} finally {
			DataSourceSwitcher.clearDataSource();
		}

		return r;
	}
}
