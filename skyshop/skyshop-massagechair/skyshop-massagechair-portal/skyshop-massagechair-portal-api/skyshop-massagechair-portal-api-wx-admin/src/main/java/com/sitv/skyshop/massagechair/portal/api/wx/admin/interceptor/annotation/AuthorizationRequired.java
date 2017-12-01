package com.sitv.skyshop.massagechair.portal.api.wx.admin.interceptor.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认为需要登录验证
 *
 * @author zfj20 @ 2017年12月8日
 */
@Target({ TYPE, METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizationRequired {

	/**
	 * 默认为true
	 * 
	 * @return
	 */
	public boolean value() default true;
}
