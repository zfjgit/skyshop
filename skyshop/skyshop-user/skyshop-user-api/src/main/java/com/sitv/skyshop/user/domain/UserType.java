package com.sitv.skyshop.user.domain;

public enum UserType {

	MEMBER(2, "会员"), SHOP(1, "店家"), MANAGER(3, "管理员");
	
	private UserType(int code, String name) {
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
