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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;
import com.sitv.skyshop.massagechair.domain.order.Order.PayType;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.device.InstallationAddressInfo;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class OrderInfo extends FullInfoDto {

	private static final long serialVersionUID = 1970592130500769199L;

	@Min(1)
	private int minutes;

	@Min(1)
	private BigDecimal money;

	@NotNull
	private MassageChairInfo chair;

	private EnumInfo<PayStatus, String> payStatus;

	private AgencyInfo agency;

	private EnumInfo<PayType, String> payType;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar endDate;

	private InstallationAddressInfo installationAddress;

	public OrderInfo(Long id, String code, int minutes, BigDecimal money, EnumInfo<PayStatus, String> payStatus, EnumInfo<PayType, String> payType, Calendar createTime,
	                Calendar updateTime, String description, MassageChairInfo massageChairInfo, AgencyInfo agency, EnumInfo<DeleteStatus, Integer> deleteStatus, String checkCode) {
		super(id, code, null, description, createTime, updateTime);
		this.minutes = minutes;
		this.money = money;
		this.payStatus = payStatus;
		this.chair = massageChairInfo;
		this.agency = agency;
		this.payType = payType;
		setCheckCode(checkCode);
		setDeleteStatus(deleteStatus);
	}

	public static OrderInfo create(Order order) {
		if (order == null) {
			return null;
		}
		return new OrderInfo(order.getId(), order.getCode(), order.getMinutes(), order.getMoney(), new EnumInfo<>(order.getPayStatus()), new EnumInfo<>(order.getPayType()),
		                order.getCreateTime(), order.getUpdateTime(), order.getDescription(), MassageChairInfo.create(order.getChair()), AgencyInfo.create(order.getAgency()),
		                new EnumInfo<>(order.getDeleteStatus()), order.getCheckCode());
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

}
