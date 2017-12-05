/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

/**
 * 按分钟计价
 *
 * @author zfj20 @ 2017年11月16日
 */
public class MinutePrice extends Price {

	public MinutePrice(Long id, String name, BigDecimal price, String img) {
		super(id, name, price, img);
	}

	public String getType() {
		return this.getClass().getSimpleName();
	}

}
