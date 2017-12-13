/**
 *
 */
package com.sitv.skyshop.common.utils.resolvers.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(PARAMETER)
/**
 * @author zfj20 @ 2017年12月11日
 */
public @interface SearchParamType {

	public Class<?> value();
}
