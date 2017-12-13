/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.common.domain.Withraw;
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
public class AgencyWithraw extends Withraw {

	private Agency agency;

	protected AgencyWithraw() {
	}

	public AgencyWithraw(Agency agency, BigDecimal money, WithrawStatus status, String bank, String account, String accountName) {
		super(money, status, bank, account, accountName);
		this.setAgency(agency);
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
		calcCheckCode();
	}

	public String calcCheckCode() {
		String raw = agency.getId() + "" + getMoney() + getStatus().getCode() + getBank() + getAccount() + getAccountName();
		String chc = Utils.digest(raw, "SHA-1");
		setCheckCode(chc);
		return chc;
	}

}
