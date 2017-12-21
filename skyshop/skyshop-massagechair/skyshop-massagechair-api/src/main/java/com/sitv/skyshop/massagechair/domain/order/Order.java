/**
 *
 */
package com.sitv.skyshop.massagechair.domain.order;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
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
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Slf4j
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

	private Calendar chairStartTime;

	private DeleteStatus deleteStatus;

	protected Order() {
	}

	public Order(String code, int minutes, BigDecimal money, PayStatus payStatus, PayType payType, MassageChair chair, Agency agency, DeleteStatus deleteStatus,
	                InstallationAddress installationAddress) {
		super("", code);
		this.minutes = minutes;
		this.money = money;
		this.payStatus = payStatus;
		this.chair = chair;
		this.setPayType(payType);
		this.setAgency(agency);
		this.deleteStatus = deleteStatus;
		this.installationAddress = installationAddress;
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
		setChairStartTime(Calendar.getInstance());
		calcCheckCode();
	}

	public String calcCheckCode() {
		String raw = getCode() + chair.getId() + getMinutes() + getMoney() + getPayStatus().getCode() + Utils.time2String(getCreateTime(), Constants.DATETIME_FORMAT_3)
		                + (agency == null ? 0 : agency.getId()) + deleteStatus.getCode();
		log.debug("raw=" + raw);
		String chc = Utils.digest(raw, "SHA-1");
		setCheckCode(chc);
		return chc;
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

	public BigDecimal getMoney() {
		money.setScale(2);
		return money;
	}

}
