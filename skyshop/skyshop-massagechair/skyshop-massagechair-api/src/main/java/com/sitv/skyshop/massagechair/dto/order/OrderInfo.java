/**
 *
 */
package com.sitv.skyshop.massagechair.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.dto.info.SimpleInfoDto;
import com.sitv.skyshop.massagechair.domain.agency.Agency.AgencyLevel;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;
import com.sitv.skyshop.massagechair.domain.order.Order.PayType;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;

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

	private int minutes;

	private BigDecimal money;

	private SimpleInfoDto chair;

	private EnumInfo<PayStatus, String> payStatus;

	private AgencyInfo agency;

	private EnumInfo<PayType, String> payType;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar endDate;

	private SimpleInfoDto installationAddress;

	private OperateResultInfo operateResultInfo;

	private long leftMinutes;

	private String contactNumber;

	public OrderInfo() {
	}

	public OrderInfo(Long id, String code, int minutes, BigDecimal money, EnumInfo<PayStatus, String> payStatus, EnumInfo<PayType, String> payType, Calendar createTime,
	                Calendar updateTime, String description, SimpleInfoDto massageChairInfo, SimpleInfoDto address, AgencyInfo agency, EnumInfo<DeleteStatus, Integer> deleteStatus,
	                String checkCode) {
		super(id, code, null, description, createTime, updateTime);
		this.minutes = minutes;
		this.money = money;
		this.payStatus = payStatus;
		this.chair = massageChairInfo;
		this.payType = payType;
		this.installationAddress = address;
		this.agency = agency;
		if (this.agency == null) {
			this.agency = new AgencyInfo();
			this.agency.setId(0l);
			this.agency.setName(AgencyLevel.SYSTEM.getName());
		}
		setCheckCode(checkCode);
		setDeleteStatus(deleteStatus);
	}

	public static OrderInfo create(Order order) {
		if (order == null) {
			return null;
		}
		return new OrderInfo(order.getId(), order.getCode(), order.getMinutes(), order.getMoney(), new EnumInfo<>(order.getPayStatus()), new EnumInfo<>(order.getPayType()),
		                order.getCreateTime(), order.getUpdateTime(), order.getDescription(), new SimpleInfoDto(order.getChair().getId(), order.getChair().getName()),
		                new SimpleInfoDto(order.getInstallationAddress().getId(), order.getInstallationAddress().getFullAddress()), AgencyInfo.create(order.getAgency()),
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
