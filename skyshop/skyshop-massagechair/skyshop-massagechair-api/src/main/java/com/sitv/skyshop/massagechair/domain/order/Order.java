/**
 *
 */
package com.sitv.skyshop.massagechair.domain.order;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.price.Price;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class Order extends DomainObject {

	private int minutes;
	private BigDecimal money;
	private MassageChair chair;
	private PayStatus payStatus;
	private Calendar completeTime;
	private OrderStatus orderStatus;

	private BigDecimal realMins;

	private Price price;

	/**
	 * @param id
	 * @param description
	 * @param minutes
	 * @param money
	 * @param realMins
	 * @param completeTime
	 * @param orderStatus
	 * @param payStatus
	 * @param chair
	 * @param price
	 */
	public Order(String code, String description, int minutes, BigDecimal money, BigDecimal realMins, Calendar completeTime, String orderStatus, String payStatus, Price price,
	                MassageChair chair) {
		super(null, code, null, description);
		this.minutes = minutes;
		this.completeTime = completeTime;
		this.money = money;
		this.orderStatus = OrderStatus.valueOf(orderStatus);
		this.payStatus = PayStatus.valueOf(payStatus);
		this.realMins = realMins;
		this.chair = chair;
		this.price = price;
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
	 * @return the orderStatus
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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

	/**
	 * @return the completeTime
	 */
	public Calendar getCompleteTime() {
		return completeTime;
	}

	/**
	 * @param completeTime
	 *            the completeTime to set
	 */
	public void setCompleteTime(Calendar completeTime) {
		this.completeTime = completeTime;
	}

	/**
	 * @return the realMins
	 */
	public BigDecimal getRealMins() {
		return realMins;
	}

	/**
	 * @param realMins
	 *            the realMins to set
	 */
	public void setRealMins(BigDecimal realMins) {
		this.realMins = realMins;
	}

	/**
	 * @return the price
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Price price) {
		this.price = price;
	}

	public enum OrderStatus implements BaseEnum<OrderStatus, String> {
		NEW("NEW", "新创建"), PROCESSING("PROCESSING", "处理中"), COMPLETED("COMPLETED", "已完成"), FAULT("FAULT", "出错");

		private String code;
		private String name;

		private OrderStatus(String code, String name) {
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
		UNPAID("UNPAID", "未支付"), PAID("PAID", "已支付");

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
