/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

/**
 * 固定价格套餐
 *
 * @author zfj20 @ 2017年11月15日
 */
public class FixedPrice extends Price {

	private int minutes;

	public FixedPrice(Long id, String name, BigDecimal price, String img, int minutes) {
		super(id, name, price, img);
		this.minutes = minutes;
	}

	public BigDecimal calcMoney(int minutes) {
		return getPrice();
	}

	public String getType() {
		return this.getClass().getSimpleName();
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}
