/**
 *
 */
package com.sitv.skyshop.massagechair.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.dto.price.PriceInfo;

/**
 * @author zfj20 @ 2017年11月20日
 */
public class OrderInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 1970592130500769199L;

	private int minutes;
	private BigDecimal money;
	private MassageChairInfo chair;
	private String payStatus;
	private Calendar completeTime;
	private String orderStatus;

	private BigDecimal realMins;

	private PriceInfo price;

	/**
	 * @param id
	 * @param minutes
	 * @param money
	 * @param payStatus
	 * @param orderStatus
	 * @param createTime
	 * @param completeTime
	 * @param realMins
	 * @param description
	 */
	public OrderInfo(Long id, String code, int minutes, BigDecimal money, String payStatus, String orderStatus, Calendar createTime, Calendar completeTime, Calendar updateTime,
	                BigDecimal realMins, String description, MassageChairInfo massageChairInfo, PriceInfo priceInfo) {
		super(id, code, null, description, createTime, updateTime);
		this.minutes = minutes;
		this.completeTime = completeTime;
		this.money = money;
		this.orderStatus = orderStatus;
		this.payStatus = payStatus;
		this.realMins = realMins;
		this.chair = massageChairInfo;
		this.price = priceInfo;
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

	public Calendar getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Calendar completeTime) {
		this.completeTime = completeTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getRealMins() {
		return realMins;
	}

	public void setRealMins(BigDecimal realMins) {
		this.realMins = realMins;
	}

	public PriceInfo getPrice() {
		return price;
	}

	public void setPrice(PriceInfo price) {
		this.price = price;
	}

	/**
	 * @param order
	 * @return
	 */
	public static OrderInfo create(Order order) {
		return new OrderInfo(order.getId(), order.getCode(), order.getMinutes(), order.getMoney(), order.getPayStatus().getCode(), order.getOrderStatus().getCode(),
		                order.getCreateTime(), order.getCompleteTime(), order.getUpdateTime(), order.getRealMins(), order.getDescription(),
		                MassageChairInfo.create(order.getChair()), PriceInfo.create(order.getPrice()));
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
