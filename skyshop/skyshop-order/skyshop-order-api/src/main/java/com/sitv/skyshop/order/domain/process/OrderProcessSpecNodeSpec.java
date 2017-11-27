package com.sitv.skyshop.order.domain.process;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.OrderStatus;

public class OrderProcessSpecNodeSpec extends DomainObject {

	private OrderProcessSpec orderProcessSpec;
	
	private OrderProcessNodeSpec orderProcessNodeSpec;
	
	private OrderStatus orderStatus;
	
	protected OrderProcessSpecNodeSpec() {
	}

	public OrderProcessSpec getOrderProcessSpec() {
		return orderProcessSpec;
	}

	public void setOrderProcessSpec(OrderProcessSpec orderProcessSpec) {
		this.orderProcessSpec = orderProcessSpec;
	}

	public OrderProcessNodeSpec getOrderProcessNodeSpec() {
		return orderProcessNodeSpec;
	}

	public void setOrderProcessNodeSpec(OrderProcessNodeSpec orderProcessNodeSpec) {
		this.orderProcessNodeSpec = orderProcessNodeSpec;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
