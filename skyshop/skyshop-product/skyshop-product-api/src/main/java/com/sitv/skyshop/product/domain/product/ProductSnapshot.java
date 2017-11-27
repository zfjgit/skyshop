package com.sitv.skyshop.product.domain.product;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;

public class ProductSnapshot extends DomainObject {

	private Long productId;
	
	private String promotion;
	
	private BigDecimal price;
	
	private BigDecimal marketPrice;
	
	private BigDecimal costPrice;
	
	protected ProductSnapshot() {
	}
	
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	
}
