package com.sitv.skyshop.order.domain.comment;

public enum CommentType {

	HIGH(1, "好评"), MIDDLE(2, "中评"), LOW(3, "差评");
	
	private int code;
	private String name;

	private CommentType(int code, String name) {
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
