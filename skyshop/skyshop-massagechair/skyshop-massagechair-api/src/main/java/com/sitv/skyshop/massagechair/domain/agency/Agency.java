/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;
import com.sitv.skyshop.domain.IDeleteStatus;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class Agency extends DomainObject implements ICheckCodeType, IDeleteStatus {

	private Agency parent;

	private AgencyLevel level;

	private int orderIncomePercentage;

	private BigDecimal balance;

	private String checkCode;

	private DeleteStatus deleteStatus;

	public Agency(Long id, String name, Agency parent, AgencyLevel level, int orderIncomePercentage, BigDecimal balance) {
		super(id, name);
		this.parent = parent;
		this.level = level;
		this.orderIncomePercentage = orderIncomePercentage;
		this.balance = balance;
		this.checkCode = calcCheckCode();
	}

	public String calcCheckCode() {
		return "";
	}

	public Agency getParent() {
		return parent;
	}

	public void setParent(Agency parent) {
		this.parent = parent;
	}

	public AgencyLevel getLevel() {
		return level;
	}

	public void setLevel(AgencyLevel level) {
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

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public enum AgencyLevel implements BaseEnum<AgencyLevel, String> {
		SYSTEM("A", "平台"), AREA_GENERAL_AGENCY("C", "区域总代"), D_AGENCY("D", "总代"), E_AGENCY("E", "代理商"), M_AGENCY("M", "代理商");

		private String code;
		private String name;

		private AgencyLevel(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

}
