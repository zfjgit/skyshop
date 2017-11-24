package com.sitv.skyshop.product.domain.product.sku;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.product.domain.product.Product;

public class SkuSnapshot extends DomainObject {
	private Long skuId;
	
	private Product product;

	protected SkuSnapshot() {}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
}
