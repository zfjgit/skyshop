/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyInfo extends FullInfoDto {

	private static final long serialVersionUID = -6151546575275681695L;

	private AgencyInfo parent;

	private EnumInfo<Agency.AgencyLevel, String> level;

	private int orderIncomePercentage;

	private BigDecimal balance;

	public AgencyInfo(Long id, String name, AgencyInfo parent, EnumInfo<Agency.AgencyLevel, String> level, int orderIncomePercentage, BigDecimal balance,
	                EnumInfo<DeleteStatus, Integer> deleteStatus, String checkCode, Calendar createTime, Calendar updateTime) {
		super(id, name, null, createTime, updateTime);
		this.level = level;
		this.parent = parent;
		this.balance = balance;
		this.orderIncomePercentage = orderIncomePercentage;
		setCheckCode(checkCode);
		setDeleteStatus(deleteStatus);
	}

	public static AgencyInfo create(Agency agency) {
		if (agency == null) {
			return null;
		}
		AgencyInfo parent = AgencyInfo.create(agency.getParent());
		return new AgencyInfo(agency.getId(), agency.getName(), parent, new EnumInfo<>(agency.getLevel()), agency.getOrderIncomePercentage(), agency.getBalance(),
		                new EnumInfo<>(agency.getDeleteStatus()), agency.getCheckCode(), agency.getCreateTime(), agency.getUpdateTime());
	}

	public static List<AgencyInfo> creates(List<Agency> agencys) {
		List<AgencyInfo> list = new ArrayList<>();
		if (agencys != null) {
			for (Agency agency : agencys) {
				list.add(create(agency));
			}
		}
		return list;
	}

}
