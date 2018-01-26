/**
 *
 */
package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class BankAccount extends DomainObject implements ICheckCodeType {

	private String bank;
	private String account;
	private String accountName;
	private String checkCode;

	protected BankAccount() {
	}

	public BankAccount(String bank, String account, String accountName) {
		super();
		this.bank = bank;
		this.account = account;
		this.accountName = accountName;
	}

}
