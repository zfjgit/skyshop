/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import com.sitv.skyshop.common.domain.BankAccount;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class AgencyBankAccount extends BankAccount {

	private Agency agency;

	public AgencyBankAccount(Agency agency, String bank, String account, String accountName) {
		super(bank, account, accountName);
		this.agency = agency;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public String calcCheckCode() {
		return null;
	}

}
