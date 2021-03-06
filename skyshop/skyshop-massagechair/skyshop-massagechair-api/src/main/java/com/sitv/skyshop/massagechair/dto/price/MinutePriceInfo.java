/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import com.sitv.skyshop.massagechair.domain.price.MinutePrice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月16日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MinutePriceInfo extends PriceInfo {

	private static final long serialVersionUID = -124350725826771712L;

	public MinutePriceInfo() {
	}

	public MinutePriceInfo(MinutePrice p) {
		super(p);
	}

	public String getType() {
		return MinutePrice.class.getSimpleName();
	}
}
