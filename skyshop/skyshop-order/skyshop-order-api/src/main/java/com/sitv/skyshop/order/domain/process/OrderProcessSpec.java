package com.sitv.skyshop.order.domain.process;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.OrderType;

public class OrderProcessSpec extends DomainObject {

	private OrderType orderType;
	
	protected OrderProcessSpec() {
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
}
