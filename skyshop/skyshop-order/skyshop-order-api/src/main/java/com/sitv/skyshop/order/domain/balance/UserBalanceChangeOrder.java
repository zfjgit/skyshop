package com.sitv.skyshop.order.domain.balance;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.Order;
import com.sitv.skyshop.user.domain.UserBalance;

public class UserBalanceChangeOrder extends DomainObject {

	private UserBalance userBalance;
	
	private BigDecimal money;
	
	private UserBalanceChangeOrderStatus status;
	
	private Order order;
	
	protected UserBalanceChangeOrder() {
	}

	public UserBalance getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(UserBalance userBalance) {
		this.userBalance = userBalance;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public UserBalanceChangeOrderStatus getStatus() {
		return status;
	}

	public void setStatus(UserBalanceChangeOrderStatus status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
