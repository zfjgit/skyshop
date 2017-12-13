/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.common.domain.Withraw.WithrawStatus;
import com.sitv.skyshop.common.dto.WithrawInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.agency.AgencyWithraw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyWithrawInfo extends WithrawInfo {

	private static final long serialVersionUID = -232028056019230325L;
	private AgencyInfo agency;

	public AgencyWithrawInfo() {
	}

	public AgencyWithrawInfo(Long id, AgencyInfo agency, BigDecimal money, EnumInfo<WithrawStatus, String> status, String bank, String account, String accountName,
	                String checkCode, Calendar createTime, Calendar updateTime) {
		super(id, money, status, bank, account, accountName, checkCode, createTime, updateTime);
		this.setAgency(agency);
	}

	public static AgencyWithrawInfo create(AgencyWithraw withraw) {
		if (withraw == null) {
			return null;
		}
		AgencyInfo agency = AgencyInfo.create(withraw.getAgency());

		return new AgencyWithrawInfo(withraw.getId(), agency, withraw.getMoney(), new EnumInfo<>(withraw.getStatus()), withraw.getBank(), withraw.getAccount(),
		                withraw.getAccountName(), withraw.getCheckCode(), withraw.getCreateTime(), withraw.getUpdateTime());
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
