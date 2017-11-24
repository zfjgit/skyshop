/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class Price extends DomainObject {

	private BigDecimal price;

	public BigDecimal calcMoney(int minutes) {
		return new BigDecimal(minutes).multiply(price);
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public abstract String getType();
}
