package com.sitv.skyshop.product.domain.category;

import com.sitv.skyshop.domain.DomainObject;

public abstract class ProductTemplate extends DomainObject {

	private ProductCategory productCategory;
	
	protected ProductTemplate() {
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
}
