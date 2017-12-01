/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 前 <code>earlierOn</code> 分钟 <code>earlierOnMoney</code> 元，
 * 超过按每分钟 <code>price</code> 元计算
 *
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PromotionPrice extends Price {

	protected PromotionPrice() {
	}

	public PromotionPrice(Long id, String name, BigDecimal price, String img) {
		super(id, name, price, img);
	}

	private int earlierOn;
	private BigDecimal earlierOnMoney;

	public BigDecimal calcMoney(int minutes) {
		if (minutes <= earlierOn) {
			return earlierOnMoney;
		}
		return super.calcMoney(minutes);
	}

}
