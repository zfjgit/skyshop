package com.sitv.skyshop.product.domain.product;

public enum SpecialOfferType {

	DAY(1, "今日特价"), WEEK(2, "本周特价"), MONTH(3, "本月特价"), NORMAL(0, "非特价");
	
	private int code;
	
	private String name;
	
	private SpecialOfferType(int code, String name) {
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
