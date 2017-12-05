/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.util.Calendar;

import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BankAccountInfo extends FullInfoDto {

	private static final long serialVersionUID = 3565737011512298807L;

	private String bank;
	private String account;
	private String accountName;

	public BankAccountInfo(Long id, String bank, String account, String accountName, String checkCode, Calendar createTime, Calendar updateTime) {
		super(id, createTime, updateTime);
		this.bank = bank;
		this.account = account;
		this.accountName = accountName;
		setCheckCode(checkCode);
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
