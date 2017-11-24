/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.massagechair.domain.price.Price;

/**
 * @author zfj20 @ 2017年11月18日
 */
public class PricePackage extends DomainObject {

	private MassageChair massageChair;

	private Price price;

	public PricePackage(MassageChair massageChair, Price price) {
		this.setMassageChair(massageChair);
		this.setPrice(price);
	}

	public MassageChair getMassageChair() {
		return massageChair;
	}

	public void setMassageChair(MassageChair massageChair) {
		this.massageChair = massageChair;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
}
