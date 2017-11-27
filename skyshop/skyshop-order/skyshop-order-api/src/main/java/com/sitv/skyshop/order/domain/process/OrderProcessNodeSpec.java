package com.sitv.skyshop.order.domain.process;

import com.sitv.skyshop.domain.DomainObject;

public class OrderProcessNodeSpec extends DomainObject {

	private boolean isExpressNode;
	
	protected OrderProcessNodeSpec() {
	}

	public boolean isExpressNode() {
		return isExpressNode;
	}

	public void setExpressNode(boolean isExpressNode) {
		this.isExpressNode = isExpressNode;
	}
	
}
