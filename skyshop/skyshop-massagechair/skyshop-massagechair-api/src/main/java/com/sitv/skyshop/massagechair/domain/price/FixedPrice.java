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

	public BigDecimal calcMoney(int minutes) {
		return getPrice();
	}

	public String getType() {
		return this.getClass().getSimpleName();
	}
}
