/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.math.BigDecimal;

import com.sitv.skyshop.dto.info.FullInfoDto;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class WithrawInfo extends FullInfoDto {

	private static final long serialVersionUID = -4022477063307462613L;

	private BigDecimal money;
	private String status;
	private String bank;
	private String account;
	private String accountName;

	public WithrawInfo(Long id, BigDecimal money, String status, String bank, String account, String accountName) {
		super(id, null);
		this.money = money;
		this.status = status;
		this.bank = bank;
		this.account = account;
		this.accountName = accountName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
