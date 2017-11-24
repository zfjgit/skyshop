package com.sitv.skyshop.product.domain.product;

import java.math.BigDecimal;

import com.sitv.skyshop.user.domain.member.MemberLevel;

public class Product_Discount extends ProductRelation {

	private MemberLevel memberLevel;
	
	private BigDecimal discount;
	
	protected Product_Discount() {
	}

	public MemberLevel getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
}
