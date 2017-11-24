package com.sitv.skyshop.user.domain.shop;

import com.sitv.skyshop.domain.DomainObject;

public class ShopServiceSerivce extends DomainObject {

	private Shop shop;
	
	private ShopService service;
	
	protected ShopServiceSerivce() {
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public ShopService getService() {
		return service;
	}

	public void setService(ShopService service) {
		this.service = service;
	}
}
