/**
 *
 */
package com.sitv.skyshop.domain;

import com.sitv.skyshop.exception.EnumCreationException;

/**
 * @author zfj20 @ 2017年11月16日
 */
public interface BaseEnum<E extends Enum<?>, T> {
	T getCode();

	String getName();

	public static <N extends BaseEnum<?, C>, C> N valueOf(Class<N> clazz, C code) {
		if (clazz == null || code == null) {
			return null;
		}

		N[] enums = clazz.getEnumConstants();

		for (N e : enums) {
			if (e.getCode().equals(code)) {
				return e;
			}
		}

		throw new EnumCreationException("未知的枚举类型：class=" + clazz.getName() + "/code=" + code);
	}

}
