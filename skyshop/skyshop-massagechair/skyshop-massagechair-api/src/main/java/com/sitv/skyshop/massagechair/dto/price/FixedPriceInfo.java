/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import com.sitv.skyshop.massagechair.domain.price.FixedPrice;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class FixedPriceInfo extends PriceInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 7683589943837941394L;

	/**
	 *
	 */
	public FixedPriceInfo() {
	}

	/**
	 * @param p
	 */
	public FixedPriceInfo(FixedPrice p) {
		super(p);
	}

	public String getType() {
		return FixedPrice.class.getSimpleName();
	}
}
