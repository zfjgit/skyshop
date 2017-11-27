package com.sitv.skyshop.user.domain;

import com.sitv.skyshop.domain.DomainObject;

public class UserBank extends DomainObject {

	private User user;
	
	private String bankName;
	private String branckName;
	private String accountNumber;
	private String accountName;
	
	private UserType userType;
	
	protected UserBank() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranckName() {
		return branckName;
	}

	public void setBranckName(String branckName) {
		this.branckName = branckName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
