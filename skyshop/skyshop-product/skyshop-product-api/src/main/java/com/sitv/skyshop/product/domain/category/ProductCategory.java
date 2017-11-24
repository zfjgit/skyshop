package com.sitv.skyshop.product.domain.category;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.user.domain.shop.Shop;

public class ProductCategory extends DomainObject {

	private Shop shop;
	
	private int level;
	
	private ProductCategory parent;
	
	private String img;
	
	protected ProductCategory() {
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
