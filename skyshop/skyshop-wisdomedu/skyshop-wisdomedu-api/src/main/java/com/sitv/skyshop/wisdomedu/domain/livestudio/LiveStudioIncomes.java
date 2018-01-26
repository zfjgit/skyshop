/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.wisdomedu.domain.user.User;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioIncomes extends DomainObject {

	private LiveStudio liveStudio;
	private UserOrder order;
	private User user;
	private IncomeType type;
	private BigDecimal incomeAmount;
	private Integer percent;
	private BigDecimal totalAmount;
	private IncomeStatus status;
	private Calendar settleTime;

	private String subjectName;
	private String subjectImg;

	@Getter
	public enum IncomeStatus implements BaseEnum<IncomeStatus, String> {
		NOT_SETTLE("1", ""), SETTLED("2", ""), FROZEN("3", "");

		private String code;
		private String name;

		private IncomeStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	@Getter
	public enum IncomeType implements BaseEnum<IncomeType, String> {
		TOPIC("1", ""), SERIESCOURSE("2", ""), VIP("3", ""), PUNCH("4", "");

		private String code;
		private String name;

		private IncomeType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
