package com.sitv.skyshop.product.domain.periphery;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.product.domain.product.Product;
import com.sitv.skyshop.user.domain.member.Member;

/**
 * 会员收藏
 * @author zfj20
 *
 */
public class MemberFavorProduct extends DomainObject {

	private Member member;
	
	private Product product;
	
	protected MemberFavorProduct() {
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
