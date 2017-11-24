package com.sitv.skyshop.product.domain.product;

import com.sitv.skyshop.domain.DomainObject;

public abstract class ProductRelation extends DomainObject {

	private Product product;
	
	protected ProductRelation() {}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
