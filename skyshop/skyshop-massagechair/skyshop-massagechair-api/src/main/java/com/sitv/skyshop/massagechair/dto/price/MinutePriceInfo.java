/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import com.sitv.skyshop.massagechair.domain.price.MinutePrice;

/**
 * @author zfj20 @ 2017年11月16日
 */
public class MinutePriceInfo extends PriceInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -124350725826771712L;

	/**
	 * @param p
	 */
	public MinutePriceInfo(MinutePrice p) {
		super(p);
	}

	public String getType() {
		return MinutePrice.class.getSimpleName();
	}
}
