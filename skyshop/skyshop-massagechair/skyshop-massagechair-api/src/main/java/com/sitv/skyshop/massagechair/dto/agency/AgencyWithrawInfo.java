/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.dto.WithrawInfo;
import com.sitv.skyshop.massagechair.domain.agency.AgencyWithraw;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class AgencyWithrawInfo extends WithrawInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -232028056019230325L;
	private AgencyInfo agency;

	public AgencyWithrawInfo(Long id, AgencyInfo agency, BigDecimal money, String status, String bank, String account, String accountName) {
		super(id, money, status, bank, account, accountName);
		this.setAgency(agency);
	}

	public AgencyInfo getAgency() {
		return agency;
	}

	public void setAgency(AgencyInfo agency) {
		this.agency = agency;
	}

	public static AgencyWithrawInfo create(AgencyWithraw withraw) {
		if (withraw == null) {
			return null;
		}
		AgencyInfo agency = AgencyInfo.create(withraw.getAgency());

		return new AgencyWithrawInfo(withraw.getId(), agency, withraw.getMoney(), withraw.getStatus().getCode(), withraw.getBank(), withraw.getAccount(), withraw.getAccountName());
	}

	public static List<AgencyWithrawInfo> creates(List<AgencyWithraw> withraws) {
		List<AgencyWithrawInfo> infos = new ArrayList<>();
		if (withraws != null) {
			for (AgencyWithraw agencyWithraw : withraws) {
				infos.add(create(agencyWithraw));
			}
		}
		return infos;
	}
}
