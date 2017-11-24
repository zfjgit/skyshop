package com.sitv.skyshop.user.domain;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.DomainObject;

public class UserBalance extends DomainObject {

	private User user;
	private BigDecimal balance;
	private BigDecimal availBalance;
	private BigDecimal freezeBalance;
	private BigDecimal prize;
	private UserType userType;
	private UserBalanceStatus status;
	
	protected UserBalance() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAvailBalance() {
		return availBalance;
	}

	public void setAvailBalance(BigDecimal availBalance) {
		this.availBalance = availBalance;
	}

	public BigDecimal getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(BigDecimal freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserBalanceStatus getStatus() {
		return status;
	}

	public void setStatus(UserBalanceStatus status) {
		this.status = status;
	}
	
}
