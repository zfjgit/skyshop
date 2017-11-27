package com.sitv.skyshop.product.domain.product.sku;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.product.domain.product.Product;

public class Sku extends DomainObject {

	private Product product;
	
	protected Sku(){}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
