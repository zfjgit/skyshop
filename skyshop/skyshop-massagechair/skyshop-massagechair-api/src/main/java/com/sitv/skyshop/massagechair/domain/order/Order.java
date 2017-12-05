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

/**
 * @author zfj20 @ 2017年11月15日
 */
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

	public Order(String code, int minutes, BigDecimal money, PayStatus payStatus, PayType payType, MassageChair chair, Agency agency) {
		super("", code);
		this.minutes = minutes;
		this.money = money;
		this.payStatus = payStatus;
		this.chair = chair;
		this.setPayType(payType);
		this.setAgency(agency);

		this.setCheckCode(calcCheckCode());
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

	/**
	 * @return the chair
	 */
	public MassageChair getChair() {
		return chair;
	}

	/**
	 * @param chair
	 *            the chair to set
	 */
	public void setChair(MassageChair chair) {
		this.chair = chair;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return the payStatus
	 */
	public PayStatus getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus
	 *            the payStatus to set
	 */
	public void setPayStatus(PayStatus payStatus) {
		this.payStatus = payStatus;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
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

	public String calcCheckCode() {
		return "";
	}

	public InstallationAddress getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(InstallationAddress installationAddress) {
		this.installationAddress = installationAddress;
	}

	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
}
