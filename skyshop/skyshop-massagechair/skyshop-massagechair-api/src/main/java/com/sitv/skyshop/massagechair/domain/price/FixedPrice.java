/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 固定价格套餐
 *
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class FixedPrice extends Price {

	private int minutes;

	protected FixedPrice() {
	}

	public FixedPrice(Long id, String name, BigDecimal price, String img, int minutes) {
		super(id, name, price, img);
		this.minutes = minutes;
	}

	public BigDecimal calcMoney(int minutes) {
		return getPrice();
	}

}
