package com.sitv.skyshop.order.domain;

import com.sitv.skyshop.domain.DomainObject;

public abstract class OrderItem extends DomainObject {

	private Order order;
	
	protected OrderItem() {
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
