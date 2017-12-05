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

	public static <N extends BaseEnum<?, C>, C> N valueOf(Class<N> clazz, C code) {
		N[] enums = clazz.getEnumConstants();

		for (N e : enums) {
			if (e.getCode().equals(code)) {
				return e;
			}
		}

		throw new IllegalArgumentException("未知的枚举类型：" + code + "，请核对" + clazz.getName());
	}

}
