package com.sitv.skyshop.product.domain.product;

public enum PrizeStrategy {

	JOIN(1, "参与"), NORMAL(0, "不参与");
	
	private int code;
	
	private String name;
	
	private PrizeStrategy(int code, String name) {
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
