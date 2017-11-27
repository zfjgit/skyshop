package com.sitv.skyshop.product.domain.product;

import com.sitv.skyshop.product.domain.category.ProductSysCategory;

public class Product_SysCategory extends ProductRelation {

	private ProductSysCategory productSysCategory;
	
	protected Product_SysCategory() {}

	public ProductSysCategory getProductSysCategory() {
		return productSysCategory;
	}

	public void setProductSysCategory(ProductSysCategory productSysCategory) {
		this.productSysCategory = productSysCategory;
	}
	
	
}
