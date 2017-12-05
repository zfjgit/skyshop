/**
 *
 */
package com.sitv.skyshop.massagechair.domain.order;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;
import com.sitv.skyshop.domain.IDeleteStatus;
import com.sitv.skyshop.massagechair.domain.agency.Agency;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Order extends DomainObject implements ICheckCodeType, IDeleteStatus {

	private int minutes;
	private Agency agency;
	private PayType payType;
	private BigDecimal money;
	private MassageChair chair;
	private PayStatus payStatus;

	private InstallationAddress installationAddress;

	private String checkCode;

	private DeleteStatus deleteStatus;

	protected Order() {
	}

	public Order(String code, int minutes, BigDecimal money, PayStatus payStatus, PayType payType, MassageChair chair, Agency agency, DeleteStatus deleteStatus) {
		super("", code);
		this.minutes = minutes;
		this.money = money;
		this.payStatus = payStatus;
		this.chair = chair;
		this.setPayType(payType);
		this.setAgency(agency);

		calcCheckCode();
	}

	public String calcCheckCode() {
		setCheckCode("");
		return "";
	}

	public enum PayType implements BaseEnum<PayType, String> {
		WEIXIN("A", "微信支付"), POINTS("B", "点数支付");

		private String code;
		private String name;

		private PayType(String code, String name) {
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

	public enum PayStatus implements BaseEnum<PayStatus, String> {
		UNPAID("B", "未支付"), PAID("A", "已支付");

		private String code;
		private String name;

		private PayStatus(String code, String name) {
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
