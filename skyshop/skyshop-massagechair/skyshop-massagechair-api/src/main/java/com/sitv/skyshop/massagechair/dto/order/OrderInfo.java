/**
 *
 */
package com.sitv.skyshop.massagechair.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.device.InstallationAddressInfo;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
public class OrderInfo extends FullInfoDto {

	private static final long serialVersionUID = 1970592130500769199L;

	@Min(1)
	private int minutes;

	@Min(1)
	private BigDecimal money;

	@NotNull
	private MassageChairInfo chair;

	private String payStatus;

	private AgencyInfo agency;

	private String payType;

	private Calendar startDate;
	private Calendar endDate;

	private InstallationAddressInfo installationAddress;

	public OrderInfo(Long id, String code, int minutes, BigDecimal money, String payStatus, String payType, Calendar createTime, Calendar updateTime, String description,
	                MassageChairInfo massageChairInfo, AgencyInfo agency) {
		super(id, code, null, description, createTime, updateTime);
		this.minutes = minutes;
		this.money = money;
		this.payStatus = payStatus;
		this.chair = massageChairInfo;
		this.agency = agency;
		this.payType = payType;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public MassageChairInfo getChair() {
		return chair;
	}

	public void setChair(MassageChairInfo chair) {
		this.chair = chair;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public static OrderInfo create(Order order) {
		if (order == null) {
			return null;
		}
		return new OrderInfo(order.getId(), order.getCode(), order.getMinutes(), order.getMoney(), order.getPayStatus().getCode(), order.getPayType().getCode(),
		                order.getCreateTime(), order.getUpdateTime(), order.getDescription(), MassageChairInfo.create(order.getChair()), AgencyInfo.create(order.getAgency()));
	}

	public static List<OrderInfo> creates(List<Order> orders) {
		List<OrderInfo> orderInfos = new ArrayList<>();
		if (orders != null) {
			for (Order p : orders) {
				orderInfos.add(create(p));
			}
		}
		return orderInfos;
	}

	public AgencyInfo getAgency() {
		return agency;
	}

	public void setAgency(AgencyInfo agency) {
		this.agency = agency;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public InstallationAddressInfo getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(InstallationAddressInfo installationAddress) {
		this.installationAddress = installationAddress;
	}
}
