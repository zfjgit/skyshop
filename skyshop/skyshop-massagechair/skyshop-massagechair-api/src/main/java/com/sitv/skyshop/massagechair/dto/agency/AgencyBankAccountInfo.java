/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.dto.BankAccountInfo;
import com.sitv.skyshop.massagechair.domain.agency.AgencyBankAccount;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class AgencyBankAccountInfo extends BankAccountInfo {

	private static final long serialVersionUID = 4986803578187514484L;

	private AgencyInfo agency;

	public AgencyBankAccountInfo(Long id, AgencyInfo agency, String bank, String account, String accountName) {
		super(id, bank, account, accountName);
		this.setAgency(agency);
	}

	public AgencyInfo getAgency() {
		return agency;
	}

	public void setAgency(AgencyInfo agency) {
		this.agency = agency;
	}

	public static AgencyBankAccountInfo create(AgencyBankAccount account) {
		if (account == null) {
			return null;
		}

		AgencyInfo agency = AgencyInfo.create(account.getAgency());
		return new AgencyBankAccountInfo(account.getId(), agency, account.getBank(), account.getAccount(), account.getAccountName());
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
