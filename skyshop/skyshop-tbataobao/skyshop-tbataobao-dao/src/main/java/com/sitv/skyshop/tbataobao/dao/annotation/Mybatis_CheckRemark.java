/**
 *
 */
package com.sitv.skyshop.tbataobao.dao.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Mybatis_CheckRemark {

}
