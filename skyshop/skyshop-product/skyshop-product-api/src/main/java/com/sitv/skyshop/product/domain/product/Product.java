package com.sitv.skyshop.product.domain.product;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.user.domain.shop.Shop;

public class Product extends DomainObject {

	private Shop shop;
	
	private ProductStatus status;
	
	private String promotion;
	
	private BigDecimal price;
	
	private BigDecimal marketPrice;
	
	private BigDecimal costPrice;
	
	private int inventroy;
	
	private int viewCount;
	
	private int salesCount;
	
	private int favoriteCount;
	
	private BigDecimal prize;
	
	private SpecialOfferType specialOfferType;
	
	private boolean isHot;
	
	private boolean isRecommend;
	
	private BigDecimal deliveryCost;
	
	private PrizeStrategy prizeStrategy;
	
	private int minSalesQty;
	
	protected Product() {
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
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

	public int getInventroy() {
		return inventroy;
	}

	public void setInventroy(int inventroy) {
		this.inventroy = inventroy;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public SpecialOfferType getSpecialOfferType() {
		return specialOfferType;
	}

	public void setSpecialOfferType(SpecialOfferType specialOfferType) {
		this.specialOfferType = specialOfferType;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public boolean isRecommend() {
		return isRecommend;
	}

	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public BigDecimal getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public PrizeStrategy getPrizeStrategy() {
		return prizeStrategy;
	}

	public void setPrizeStrategy(PrizeStrategy prizeStrategy) {
		this.prizeStrategy = prizeStrategy;
	}

	public int getMinSalesQty() {
		return minSalesQty;
	}

	public void setMinSalesQty(int minSalesQty) {
		this.minSalesQty = minSalesQty;
	}
}
