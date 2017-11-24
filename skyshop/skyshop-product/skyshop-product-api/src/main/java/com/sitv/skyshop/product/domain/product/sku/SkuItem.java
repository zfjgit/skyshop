package com.sitv.skyshop.product.domain.product.sku;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;

public class SkuItem extends DomainObject {

	private Sku sku;
	
	private BigDecimal price;
	
	private String img;
	
	protected SkuItem() {
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
