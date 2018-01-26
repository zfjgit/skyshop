/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.user;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class UserOrder extends DomainObject {

	private LiveStudio liveStudio;
	private User user;
	private OrderStatus status;
	private PayType payType;
	private String payTransactionId;
	private BigDecimal totalAmount;
	private BigDecimal paidAmount;
	private BigDecimal discountAmount;
	private String remark;
	private Calendar payTime;

	@Getter
	public enum OrderStatus implements BaseEnum<OrderStatus, String> {
		NOT_PAY("1", ""), PAID("2", "");

		private String code;
		private String name;

		private OrderStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	@Getter
	public enum PayType implements BaseEnum<PayType, String> {
		WX("1", ""), BANK("2", ""), ALIPAY("3", ""), POINT("4", "");

		private String code;
		private String name;

		private PayType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
