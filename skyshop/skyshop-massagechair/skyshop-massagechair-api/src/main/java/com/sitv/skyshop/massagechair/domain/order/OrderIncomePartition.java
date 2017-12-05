/**
 *
 */
package com.sitv.skyshop.massagechair.domain.order;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class OrderIncomePartition extends DomainObject implements ICheckCodeType {

	private Order order;
	private Agency agency;

	private int percentage;
	private BigDecimal money;
	private BigDecimal totalMoney;

	private String checkCode;

	public OrderIncomePartition(Order order, Agency agency, int percentage, BigDecimal money, BigDecimal totalMoney) {
		super();
		this.order = order;
		this.agency = agency;
		this.percentage = percentage;
		this.money = money;
		this.totalMoney = totalMoney;
		this.checkCode = calcCheckCode();
	}

	public String calcCheckCode() {
		return "";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
