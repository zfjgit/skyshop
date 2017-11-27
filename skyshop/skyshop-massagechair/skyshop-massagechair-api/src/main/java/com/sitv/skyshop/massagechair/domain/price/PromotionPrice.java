/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

/**
 * 前 <code>earlierOn</code> 分钟 <code>earlierOnMoney</code> 元，
 * 超过按每分钟 <code>price</code> 元计算
 *
 * @author zfj20 @ 2017年11月15日
 */
public class PromotionPrice extends Price {

	private int earlierOn;
	private BigDecimal earlierOnMoney;

	public BigDecimal calcMoney(int minutes) {
		if (minutes <= earlierOn) {
			return earlierOnMoney;
		}
		return super.calcMoney(minutes);
	}

	/**
	 * @return the earlierOn
	 */
	public int getEarlierOn() {
		return earlierOn;
	}

	/**
	 * @param earlierOn
	 *            the earlierOn to set
	 */
	public void setEarlierOn(int earlierOn) {
		this.earlierOn = earlierOn;
	}

	/**
	 * @return the earlierOnMoney
	 */
	public BigDecimal getEarlierOnMoney() {
		return earlierOnMoney;
	}

	/**
	 * @param earlierOnMoney
	 *            the earlierOnMoney to set
	 */
	public void setEarlierOnMoney(BigDecimal earlierOnMoney) {
		this.earlierOnMoney = earlierOnMoney;
	}

	public String getType() {
		return this.getClass().getSimpleName();
	}
}
