package com.sitv.skyshop.product.domain.product;

import com.sitv.skyshop.user.domain.shop.ShopService;

public class Product_Service extends ProductRelation {

	private ShopService shopService;
	
	protected Product_Service() {
	}

	public ShopService getShopService() {
		return shopService;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	
}
