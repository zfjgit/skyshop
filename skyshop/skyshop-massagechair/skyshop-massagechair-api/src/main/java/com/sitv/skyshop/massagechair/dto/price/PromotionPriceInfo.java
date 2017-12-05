/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import java.math.BigDecimal;

import com.sitv.skyshop.massagechair.domain.price.PromotionPrice;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
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
		super(price.getId(), price.getName(), price.getPrice(), price.getImg(), AgencyInfo.create(price.getAgency()), price.getCreateTime(), price.getUpdateTime());
		this.earlierOn = price.getEarlierOn();
		this.earlierOnMoney = price.getEarlierOnMoney();
	}

	public String getType() {
		return PromotionPrice.class.getSimpleName();
	}
}
