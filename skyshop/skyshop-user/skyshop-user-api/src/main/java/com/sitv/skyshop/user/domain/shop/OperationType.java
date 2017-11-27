package com.sitv.skyshop.user.domain.shop;

public enum OperationType {
	SELF_EMPLOYED(1, "自营"), NON_SELF_EMPLOYED(0, "非自营");
	
	private int code;
	private String name;

	private OperationType(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
