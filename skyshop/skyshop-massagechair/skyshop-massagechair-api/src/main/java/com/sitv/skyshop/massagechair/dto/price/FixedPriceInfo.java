/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import com.sitv.skyshop.massagechair.domain.price.FixedPrice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
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

}
