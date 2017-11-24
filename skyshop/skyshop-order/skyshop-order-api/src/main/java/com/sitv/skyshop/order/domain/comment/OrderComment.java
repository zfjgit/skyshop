package com.sitv.skyshop.order.domain.comment;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.order.domain.Order;
import com.sitv.skyshop.user.domain.member.Member;

public class OrderComment extends DomainObject {

	private Order order;
	
	private int productStar;
	private int serviceStar;
	private int logisticStar;
	
	private Member member;
	
	protected OrderComment() {
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getProductStar() {
		return productStar;
	}

	public void setProductStar(int productStar) {
		this.productStar = productStar;
	}

	public int getServiceStar() {
		return serviceStar;
	}

	public void setServiceStar(int serviceStar) {
		this.serviceStar = serviceStar;
	}

	public int getLogisticStar() {
		return logisticStar;
	}

	public void setLogisticStar(int logisticStar) {
		this.logisticStar = logisticStar;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
}
