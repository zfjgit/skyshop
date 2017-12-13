/**
 *
 */
package com.sitv.skyshop.massagechair.domain.order;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class OrderIncomePartition extends DomainObject implements ICheckCodeType {

	private Order order;
	private Agency agency;

	private int percentage;
	private BigDecimal money;
	private BigDecimal totalMoney;

	private String checkCode;

	protected OrderIncomePartition() {
	}

	public OrderIncomePartition(Order order, Agency agency, int percentage, BigDecimal money, BigDecimal totalMoney) {
		super();
		this.order = order;
		this.agency = agency;
		this.percentage = percentage;
		this.money = money;
		this.totalMoney = totalMoney;
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
		calcCheckCode();
	}

	public String calcCheckCode() {
		String raw = order.getId() + "" + (agency == null ? 0 : agency.getId()) + getMoney() + getPercentage() + getTotalMoney()
		                + Utils.time2String(getCreateTime(), Constants.DATETIME_FORMAT_3);
		String chc = Utils.digest(raw, "SHA-1");
		setCheckCode(chc);
		return chc;
	}

	public BigDecimal getMoney() {
		money.setScale(2);
		return money;
	}

	public BigDecimal getTotalMoney() {
		totalMoney.setScale(2);
		return totalMoney;
	}
}
