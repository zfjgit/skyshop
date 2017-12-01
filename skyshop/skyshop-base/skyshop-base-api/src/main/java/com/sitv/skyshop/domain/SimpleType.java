package com.sitv.skyshop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class SimpleType implements IBaseType {

	private Long id;

	private String name;

	private String code;

	protected SimpleType() {
	}

	public SimpleType(Long id) {
		super();
		this.id = id;
	}

	public SimpleType(Long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public SimpleType(String name, String code) {
		this(null, name, code);
	}

}
