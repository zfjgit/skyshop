/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.common.dto.BankAccountInfo;
import com.sitv.skyshop.massagechair.domain.agency.AgencyBankAccount;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyBankAccountInfo extends BankAccountInfo {

	private static final long serialVersionUID = 4986803578187514484L;

	private AgencyInfo agency;

	public AgencyBankAccountInfo(Long id, AgencyInfo agency, String bank, String account, String accountName, String checkCode, Calendar createTime, Calendar updateTime) {
		super(id, bank, account, accountName, checkCode, createTime, updateTime);
		this.setAgency(agency);
	}

	public static AgencyBankAccountInfo create(AgencyBankAccount account) {
		if (account == null) {
			return null;
		}

		AgencyInfo agency = AgencyInfo.create(account.getAgency());
		return new AgencyBankAccountInfo(account.getId(), agency, account.getBank(), account.getAccount(), account.getAccountName(), account.getCheckCode(),
		                account.getCreateTime(), account.getUpdateTime());
	}

	public static List<AgencyBankAccountInfo> creates(List<AgencyBankAccount> accounts) {
		List<AgencyBankAccountInfo> infos = new ArrayList<>();
		if (accounts != null) {
			for (AgencyBankAccount account : accounts) {
				infos.add(create(account));
			}
		}
		return infos;
	}

}
