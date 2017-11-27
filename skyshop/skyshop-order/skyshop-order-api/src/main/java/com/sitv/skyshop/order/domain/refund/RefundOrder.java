package com.sitv.skyshop.order.domain.refund;

import com.sitv.skyshop.order.domain.Order;
import com.sitv.skyshop.order.domain.purchase.PurchaseOrder;

public class RefundOrder extends Order {

	private PurchaseOrder order;
	
	protected RefundOrder() {
	}

	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}
}
