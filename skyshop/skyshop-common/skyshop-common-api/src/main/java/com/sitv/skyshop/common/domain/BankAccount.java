/**
 *
 */
package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;

/**
 * @author zfj20 @ 2017年12月4日
 */
public abstract class BankAccount extends DomainObject implements ICheckCodeType {

	private String bank;
	private String account;
	private String accountName;

	public BankAccount(String bank, String account, String accountName) {
		super();
		this.bank = bank;
		this.account = account;
		this.accountName = accountName;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
