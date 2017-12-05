/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import java.math.BigDecimal;

import com.sitv.skyshop.common.domain.Withraw;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class AgencyWithraw extends Withraw {

	private Agency agency;

	public AgencyWithraw(Agency agency, BigDecimal money, WithrawStatus status, String bank, String account, String accountName) {
		super(money, status, bank, account, accountName);
		this.setAgency(agency);
	}

	public String calcCheckCode() {
		return "";
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

}
