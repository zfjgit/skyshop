/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 按分钟计价
 *
 * @author zfj20 @ 2017年11月16日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MinutePrice extends Price {

	protected MinutePrice() {
	}

	public MinutePrice(Long id, String name, BigDecimal price, String img) {
		super(id, name, price, img);
	}

	public String getType() {
		return this.getClass().getSimpleName();
	}

}
