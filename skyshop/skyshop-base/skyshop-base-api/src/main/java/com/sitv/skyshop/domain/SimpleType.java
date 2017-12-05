package com.sitv.skyshop.domain;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
