/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class AgencyInfo extends FullInfoDto {

	private static final long serialVersionUID = -6151546575275681695L;

	private AgencyInfo parent;

	private String level;

	private int orderIncomePercentage;

	private BigDecimal balance;

	public AgencyInfo(Long id, String name, AgencyInfo parent, String level, int orderIncomePercentage, BigDecimal balance) {
		super(id, name);
		this.parent = parent;
		this.level = level;
		this.orderIncomePercentage = orderIncomePercentage;
		this.balance = balance;
	}

	public AgencyInfo getParent() {
		return parent;
	}

	public void setParent(AgencyInfo parent) {
		this.parent = parent;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getOrderIncomePercentage() {
		return orderIncomePercentage;
	}

	public void setOrderIncomePercentage(int orderIncomePercentage) {
		this.orderIncomePercentage = orderIncomePercentage;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public static AgencyInfo create(Agency agency) {
		AgencyInfo parent = AgencyInfo.create(agency.getParent());
		return new AgencyInfo(agency.getId(), agency.getName(), parent, agency.getLevel().getCode(), agency.getOrderIncomePercentage(), agency.getBalance());
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
