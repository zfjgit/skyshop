/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import com.sitv.skyshop.common.domain.BankAccount;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyBankAccount extends BankAccount {

	private Agency agency;

	protected AgencyBankAccount() {
	}

	public AgencyBankAccount(Agency agency, String bank, String account, String accountName) {
		super(bank, account, accountName);
		this.agency = agency;
		calcCheckCode();
	}

	public String calcCheckCode() {
		setCheckCode("");
		return "";
	}

}
