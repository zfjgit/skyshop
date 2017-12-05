/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import java.math.BigDecimal;

import com.sitv.skyshop.massagechair.domain.price.PromotionPrice;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class PromotionPriceInfo extends PriceInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 7273513643743911948L;

	private int earlierOn;
	private BigDecimal earlierOnMoney;

	/**
	 *
	 */
	public PromotionPriceInfo() {
	}

	public PromotionPriceInfo(PromotionPrice price) {
		super(price.getId(), price.getName(), price.getPrice(), price.getImg(), price.getCreateTime(), price.getUpdateTime());
		this.earlierOn = price.getEarlierOn();
		this.earlierOnMoney = price.getEarlierOnMoney();
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
		return PromotionPrice.class.getSimpleName();
	}
}
