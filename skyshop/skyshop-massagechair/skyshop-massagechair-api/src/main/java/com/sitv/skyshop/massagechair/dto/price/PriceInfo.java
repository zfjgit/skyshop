/**
 *
 */
package com.sitv.skyshop.massagechair.dto.price;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.price.FixedPrice;
import com.sitv.skyshop.massagechair.domain.price.MinutePrice;
import com.sitv.skyshop.massagechair.domain.price.Price;
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
public class PriceInfo extends FullInfoDto {

	private static final long serialVersionUID = 8365410282988425759L;

	private BigDecimal price;

	private String img;

	private AgencyInfo agency;

	public PriceInfo() {
	}

	public PriceInfo(Price price) {
		this(price.getId(), price.getName(), price.getPrice(), price.getImg(), AgencyInfo.create(price.getAgency()), price.getCreateTime(), price.getUpdateTime());
	}

	public PriceInfo(Long id, String name, BigDecimal price, String img, AgencyInfo agencyInfo, Calendar createTime, Calendar updateTime) {
		super(id, name, null, createTime, updateTime);
		this.price = price;
		this.setAgency(agencyInfo);
	}

	public String getType() {
		throw new RuntimeException("需要重写");
	}

	@SuppressWarnings("unchecked")
	public static <I extends PriceInfo, T extends Price> I create(T p) {
		if (p != null) {
			if (p instanceof PromotionPrice) {
				return (I) new PromotionPriceInfo((PromotionPrice) p);
			} else if (p instanceof FixedPrice) {
				return (I) new FixedPriceInfo((FixedPrice) p);
			} else if (p instanceof MinutePrice) {
				return (I) new MinutePriceInfo((MinutePrice) p);
			}
		}
		return null;
	}

	public static <I extends PriceInfo, T extends Price> List<I> creates(List<T> prices) {
		List<I> priceInfos = new ArrayList<>();
		if (prices != null) {
			for (Price p : prices) {
				priceInfos.add(create(p));
			}
		}
		return priceInfos;
	}

}
