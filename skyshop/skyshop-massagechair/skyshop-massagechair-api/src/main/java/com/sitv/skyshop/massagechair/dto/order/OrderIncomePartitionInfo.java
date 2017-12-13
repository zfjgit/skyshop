/**
 *
 */
package com.sitv.skyshop.massagechair.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class OrderIncomePartitionInfo extends FullInfoDto {

	private static final long serialVersionUID = 1227474544730736706L;

	private OrderInfo order;
	private AgencyInfo agency;

	private int percentage;
	private BigDecimal money;
	private BigDecimal totalMoney;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar endDate;

	public OrderIncomePartitionInfo() {
	}

	public OrderIncomePartitionInfo(Long id, OrderInfo order, AgencyInfo agency, int percentage, BigDecimal money, BigDecimal totalMoney, String checkCode, Calendar createTime,
	                Calendar updateTime) {
		super(id, createTime, updateTime);
		this.order = order;
		this.agency = agency;
		this.percentage = percentage;
		this.money = money;
		this.totalMoney = totalMoney;
		setCheckCode(checkCode);
	}

	public static OrderIncomePartitionInfo create(OrderIncomePartition orderIncomePartition) {
		if (orderIncomePartition == null) {
			return null;
		}
		OrderInfo order = OrderInfo.create(orderIncomePartition.getOrder());
		AgencyInfo agency = AgencyInfo.create(orderIncomePartition.getAgency());

		return new OrderIncomePartitionInfo(orderIncomePartition.getId(), order, agency, orderIncomePartition.getPercentage(), orderIncomePartition.getMoney(),
		                orderIncomePartition.getTotalMoney(), orderIncomePartition.getCheckCode(), orderIncomePartition.getCreateTime(), orderIncomePartition.getUpdateTime());
	}

	public static List<OrderIncomePartitionInfo> creates(List<OrderIncomePartition> list) {
		List<OrderIncomePartitionInfo> infos = new ArrayList<>();
		if (list != null) {
			for (OrderIncomePartition orderIncomePartition : list) {
				infos.add(create(orderIncomePartition));
			}
		}
		return infos;
	}
}
