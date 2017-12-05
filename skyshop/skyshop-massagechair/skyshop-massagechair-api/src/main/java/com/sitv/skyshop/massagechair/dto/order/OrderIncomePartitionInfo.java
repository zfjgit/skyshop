/**
 *
 */
package com.sitv.skyshop.massagechair.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.order.OrderIncomePartition;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class OrderIncomePartitionInfo extends FullInfoDto {

	private static final long serialVersionUID = 1227474544730736706L;

	private OrderInfo order;
	private AgencyInfo agency;

	private int percentage;
	private BigDecimal money;
	private BigDecimal totalMoney;

	public OrderIncomePartitionInfo(OrderInfo order, AgencyInfo agency, int percentage, BigDecimal money, BigDecimal totalMoney) {
		super();
		this.order = order;
		this.agency = agency;
		this.percentage = percentage;
		this.money = money;
		this.totalMoney = totalMoney;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public AgencyInfo getAgency() {
		return agency;
	}

	public void setAgency(AgencyInfo agency) {
		this.agency = agency;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public static OrderIncomePartitionInfo create(OrderIncomePartition orderIncomePartition) {
		if (orderIncomePartition == null) {
			return null;
		}
		OrderInfo order = OrderInfo.create(orderIncomePartition.getOrder());
		AgencyInfo agency = AgencyInfo.create(orderIncomePartition.getAgency());

		return new OrderIncomePartitionInfo(order, agency, orderIncomePartition.getPercentage(), orderIncomePartition.getMoney(), orderIncomePartition.getTotalMoney());
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
