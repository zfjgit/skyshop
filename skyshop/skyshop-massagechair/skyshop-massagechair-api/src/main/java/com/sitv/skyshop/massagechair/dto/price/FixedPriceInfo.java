/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import com.sitv.skyshop.massagechair.domain.price.FixedPrice;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class FixedPriceInfo extends PriceInfo {

	private static final long serialVersionUID = 7683589943837941394L;

	private int minutes;

	public FixedPriceInfo() {
	}

	public FixedPriceInfo(FixedPrice p) {
		super(p);
		this.minutes = p.getMinutes();
	}

	public String getType() {
		return FixedPrice.class.getSimpleName();
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}
