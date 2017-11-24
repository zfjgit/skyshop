package com.sitv.skyshop.product.domain.category;

import com.sitv.skyshop.user.domain.shop.ShopService;

public class ProductServiceTemplate extends ProductTemplate {

	private ShopService shopService;
	
	protected ProductServiceTemplate() {
	}

	public ShopService getShopService() {
		return shopService;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	
}
