/**
 *
 */
package com.sitv.skyshop.common.domain;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class Withraw extends DomainObject implements ICheckCodeType {

	private BigDecimal money;
	private WithrawStatus status;
	private String bank;
	private String account;
	private String accountName;
	private String checkCode;

	protected Withraw() {
	}

	public Withraw(BigDecimal money, WithrawStatus status, String bank, String account, String accountName) {
		super();
		this.money = money;
		this.status = status;
		this.bank = bank;
		this.account = account;
		this.accountName = accountName;
		calcCheckCode();
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public enum WithrawStatus implements BaseEnum<WithrawStatus, String> {
		NEW("A", "待审核"), PROCESSING("B", "处理中"), DONE("C", "提现成功"), FAILED("D", "提现失败");

		private String code;
		private String name;

		private WithrawStatus(String code, String name) {
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

	public BigDecimal getMoney() {
		money.setScale(2);
		return money;
	}
}
