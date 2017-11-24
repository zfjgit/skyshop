/**
 *
 */
package com.sitv.skyshop.domain;

/**
 * @author zfj20 @ 2017年11月16日
 */
public interface BaseEnum<E extends Enum<?>, T> {
	T getCode();

	String getName();
}
