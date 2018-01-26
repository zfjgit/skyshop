/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.user;

import java.math.BigDecimal;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioCoupon;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class UserOrderItem extends DomainObject {

	private UserOrder order;
	private DomainObject subject;
	private SubjectType subjectType;
	private String subjectName;
	private String subjectImg;
	private Integer qty;
	private BigDecimal price;

	private LiveStudioCoupon coupon;
	private String couponName;
	private BigDecimal couponMoney;

	private boolean isGroupBuy;

	private BigDecimal discountPrice;

	private String remark;

	@Getter
	public enum SubjectType implements BaseEnum<SubjectType, String> {
		TOPIC("1", ""), SERIESCOURSE("2", ""), PUNCH("3", ""), VIP("4", ""), ASK("5", "");

		private String code;
		private String name;

		private SubjectType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
