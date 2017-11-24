package com.sitv.skyshop.product.domain.product;

import com.sitv.skyshop.product.domain.category.ProductCategory;

public class Product_Category extends ProductRelation {

	private ProductCategory productCategory;
	
	protected Product_Category() {
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
}
