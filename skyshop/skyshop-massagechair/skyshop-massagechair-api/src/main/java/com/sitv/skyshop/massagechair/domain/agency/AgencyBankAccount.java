/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import java.util.Calendar;

import com.sitv.skyshop.common.domain.BankAccount;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;

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
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
		calcCheckCode();
	}

	public String calcCheckCode() {
		String raw = agency.getId() + "" + getBank() + getAccount() + getAccountName() + Utils.time2String(getCreateTime(), Constants.DATETIME_FORMAT_3);
		String chc = Utils.digest(raw, "SHA-1");
		setCheckCode(chc);
		return chc;
	}

}
