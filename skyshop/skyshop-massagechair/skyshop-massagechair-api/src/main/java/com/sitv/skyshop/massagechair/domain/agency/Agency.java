/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;
import com.sitv.skyshop.domain.IDeleteStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Agency extends DomainObject implements ICheckCodeType, IDeleteStatus {

	private Agency parent;

	private AgencyLevel level;

	private int orderIncomePercentage;

	private BigDecimal balance;

	private String checkCode;

	private DeleteStatus deleteStatus;

	protected Agency() {
	}

	public Agency(Long id, String name, Agency parent, AgencyLevel level, int orderIncomePercentage, BigDecimal balance, DeleteStatus deleteStatus) {
		super(id, name);
		this.parent = parent;
		this.level = level;
		this.orderIncomePercentage = orderIncomePercentage;
		this.balance = balance;
		calcCheckCode();
	}

	public Agency(long id) {
		super(id);
	}

	public String calcCheckCode() {
		String newCheckCode = "";
		setCheckCode(newCheckCode);
		return newCheckCode;
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

}
