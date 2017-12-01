/**
 *
 */
package com.sitv.skyshop.dto.info;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.Dto;
import com.sitv.skyshop.exception.EnumCreationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月9日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class EnumInfo<E extends BaseEnum<?, T>, T> extends Dto {
	private static final long serialVersionUID = -2928738275800322632L;

	private T code;
	private String name;

	public EnumInfo() {
	}

	public EnumInfo(E e) {
		if (e != null) {
			this.code = e.getCode();
			this.name = e.getName();
		}
	}

	public EnumInfo(Class<E> c, T code) {
		super();

		if (c == null || code == null) {
			throw new EnumCreationException("枚举类型生成失败：class=" + c.getName() + "/code=" + code);
		}

		E[] enums = c.getEnumConstants();
		for (E e : enums) {
			if (e.getCode().equals(code)) {
				this.code = e.getCode();
				this.name = e.getName();
				break;
			}
		}
		if (this.code == null) {
			throw new EnumCreationException("枚举类型生成失败：class=" + c.getName() + "/code=" + code);
		}
	}

	public EnumInfo(T code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

}
