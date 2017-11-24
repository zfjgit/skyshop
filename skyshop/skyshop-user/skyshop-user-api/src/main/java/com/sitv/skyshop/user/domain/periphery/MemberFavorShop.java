package com.sitv.skyshop.user.domain.periphery;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.user.domain.member.Member;
import com.sitv.skyshop.user.domain.shop.Shop;

/**
 * 收藏店铺
 * @author zfj20
 *
 */
public class MemberFavorShop extends DomainObject {

	private Member member;
	
	private Shop shop;
	
	protected MemberFavorShop() {
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
