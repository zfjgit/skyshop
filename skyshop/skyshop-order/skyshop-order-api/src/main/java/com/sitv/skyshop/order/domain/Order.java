package com.sitv.skyshop.order.domain;

import com.sitv.skyshop.domain.DomainObject;

public abstract class Order extends DomainObject {

	private OrderType orderType;
	
	private OrderStatus orderStatus;
	
	private OrderFrom orderFrom;
	
	private boolean isDelete;
	
	protected Order() {
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderFrom getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(OrderFrom orderFrom) {
		this.orderFrom = orderFrom;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}
