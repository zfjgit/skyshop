/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.massagechair.domain.price.Price;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PricePackage extends DomainObject {

	private MassageChair massageChair;

	private Price price;

	protected PricePackage() {
	}

	public PricePackage(MassageChair massageChair, Price price) {
		this.setMassageChair(massageChair);
		this.setPrice(price);
	}

}
