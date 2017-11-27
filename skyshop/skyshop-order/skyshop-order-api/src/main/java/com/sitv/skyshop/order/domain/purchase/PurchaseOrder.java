package com.sitv.skyshop.order.domain.purchase;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.order.domain.Express;
import com.sitv.skyshop.order.domain.Order;
import com.sitv.skyshop.user.domain.Receiver;
import com.sitv.skyshop.user.domain.member.Member;
import com.sitv.skyshop.user.domain.shop.Shop;

public class PurchaseOrder extends Order {

	private Member member;

	private BigDecimal total;

	private BigDecimal realPaid;

	private Shop shop;

	private Calendar payTime;

	private Calendar deliveryTime;

	private Calendar receiptTime;

	private BigDecimal deliveryCost;

	private Receiver receiver;

	private BigDecimal newTotal;

	private BigDecimal newRealPaid;

	private BigDecimal newDeliveryCost;

	private BigDecimal discount;

	private Express express;

	protected PurchaseOrder() {
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Calendar getPayTime() {
		return payTime;
	}

	public void setPayTime(Calendar payTime) {
		this.payTime = payTime;
	}

	public Calendar getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Calendar deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Calendar getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Calendar receiptTime) {
		this.receiptTime = receiptTime;
	}

	public BigDecimal getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getNewTotal() {
		return newTotal;
	}

	public void setNewTotal(BigDecimal newTotal) {
		this.newTotal = newTotal;
	}

	public BigDecimal getNewRealPaid() {
		return newRealPaid;
	}

	public void setNewRealPaid(BigDecimal newRealPaid) {
		this.newRealPaid = newRealPaid;
	}

	public BigDecimal getNewDeliveryCost() {
		return newDeliveryCost;
	}

	public void setNewDeliveryCost(BigDecimal newDeliveryCost) {
		this.newDeliveryCost = newDeliveryCost;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getRealPaid() {
		return realPaid;
	}

	public void setRealPaid(BigDecimal realpaid) {
		this.realPaid = realpaid;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
