package com.sitv.skyshop.user.domain;

public enum Gender {
	
	MALE(1, "男"), FEMALE(0, "女"), OTHER(2, "其他");
	
	private Gender(int code, String name) {
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
