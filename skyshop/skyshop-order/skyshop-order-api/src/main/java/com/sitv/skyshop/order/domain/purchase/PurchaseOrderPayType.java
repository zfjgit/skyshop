package com.sitv.skyshop.order.domain.purchase;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.PayType;

public class PurchaseOrderPayType extends DomainObject {

	private PurchaseOrder purchaseOrder;
	
	private PayType payType;
	
	protected PurchaseOrderPayType() {
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}
	
}
