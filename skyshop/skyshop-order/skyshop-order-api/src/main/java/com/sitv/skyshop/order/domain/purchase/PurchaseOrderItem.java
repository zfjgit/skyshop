package com.sitv.skyshop.order.domain.purchase;

import java.math.BigDecimal;

import com.sitv.skyshop.order.domain.OrderItem;
import com.sitv.skyshop.product.domain.product.PrizeStrategy;
import com.sitv.skyshop.product.domain.product.Product;
import com.sitv.skyshop.product.domain.product.sku.Sku;

public class PurchaseOrderItem extends OrderItem {

	private Product product;
	
	private Integer qty;
	
	private Sku sku;
	
	private String productName;
	
	private BigDecimal price;
	
	private PrizeStrategy prizeStrategy;
	
	private BigDecimal prize;
	
	private BigDecimal deliveryCost;
	
	protected PurchaseOrderItem() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public PrizeStrategy getPrizeStrategy() {
		return prizeStrategy;
	}

	public void setPrizeStrategy(PrizeStrategy prizeStrategy) {
		this.prizeStrategy = prizeStrategy;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public BigDecimal getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	
}
