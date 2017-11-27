package com.sitv.skyshop.order.domain.balance;

public enum UserBalanceChangeOrderStatus {

	BILL(102, "已出账"), NEW(101, "未出账");
	
	private int code;
	private String name;

	private UserBalanceChangeOrderStatus(int code, String name) {
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
