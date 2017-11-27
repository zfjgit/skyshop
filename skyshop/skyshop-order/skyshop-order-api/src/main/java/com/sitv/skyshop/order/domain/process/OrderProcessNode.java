package com.sitv.skyshop.order.domain.process;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.Express;

public class OrderProcessNode extends DomainObject {

	private OrderProcess process;
	
	private OrderProcessNodeSpec orderProcessNodeSpec;
	
	private OrderProcessNodeStatus orderProcessNodeStatus;
	
	private Express express;
	
	protected OrderProcessNode() {
	}

	public OrderProcess getProcess() {
		return process;
	}

	public void setProcess(OrderProcess process) {
		this.process = process;
	}

	public OrderProcessNodeSpec getOrderProcessNodeSpec() {
		return orderProcessNodeSpec;
	}

	public void setOrderProcessNodeSpec(OrderProcessNodeSpec orderProcessNodeSpec) {
		this.orderProcessNodeSpec = orderProcessNodeSpec;
	}

	public OrderProcessNodeStatus getOrderProcessNodeStatus() {
		return orderProcessNodeStatus;
	}

	public void setOrderProcessNodeStatus(OrderProcessNodeStatus orderProcessNodeStatus) {
		this.orderProcessNodeStatus = orderProcessNodeStatus;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}
	
}
