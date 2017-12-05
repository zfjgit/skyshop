/**
 *
 */
package com.sitv.skyshop.massagechair.domain.price;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class Price extends DomainObject {

	private BigDecimal price;
	private String img;

	private Agency agency;

	protected Price() {
	}

	public Price(Long id, String name, BigDecimal price, String img) {
		super(id, name);
		this.price = price;
		this.img = img;
	}

	public BigDecimal calcMoney(int minutes) {
		return new BigDecimal(minutes).multiply(price);
	}

}
