package com.sitv.skyshop.user.domain;

public enum UserBalanceStatus {

	NORMAL(201, ""), FREEZE(202, ""), CLOSED(203, "");
	
	private UserBalanceStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	private int code;
	private String name;
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
