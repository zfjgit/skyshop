package com.sitv.skyshop.order.domain.process;

import java.util.Calendar;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.Order;

public class OrderProcess extends DomainObject {

	private Order order;
	
	private OrderProcessSpec orderProcessSpec;
	
	private Calendar completeTime;
	
	private Calendar cancelTime;
	
	protected OrderProcess() {
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderProcessSpec getOrderProcessSpec() {
		return orderProcessSpec;
	}

	public void setOrderProcessSpec(OrderProcessSpec orderProcessSpec) {
		this.orderProcessSpec = orderProcessSpec;
	}

	public Calendar getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Calendar completeTime) {
		this.completeTime = completeTime;
	}

	public Calendar getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Calendar cancelTime) {
		this.cancelTime = cancelTime;
	}
	
}
