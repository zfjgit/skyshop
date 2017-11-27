package com.sitv.skyshop.order.domain.process;

import com.sitv.skyshop.domain.DomainObject;

public class OrderProcessNodeSwitcher extends DomainObject {

	private OrderProcessNodeMatcher matcher;
	
	private OrderProcessNodeSpec lastNodeSpec;
	
	private OrderProcessNodeSpec nextNodeSpec;
	
	protected OrderProcessNodeSwitcher() {
	}

	public OrderProcessNodeMatcher getMatcher() {
		return matcher;
	}

	public void setMatcher(OrderProcessNodeMatcher matcher) {
		this.matcher = matcher;
	}

	public OrderProcessNodeSpec getLastNodeSpec() {
		return lastNodeSpec;
	}

	public void setLastNodeSpec(OrderProcessNodeSpec lastNodeSpec) {
		this.lastNodeSpec = lastNodeSpec;
	}

	public OrderProcessNodeSpec getNextNodeSpec() {
		return nextNodeSpec;
	}

	public void setNextNodeSpec(OrderProcessNodeSpec nextNodeSpec) {
		this.nextNodeSpec = nextNodeSpec;
	}
	
}
