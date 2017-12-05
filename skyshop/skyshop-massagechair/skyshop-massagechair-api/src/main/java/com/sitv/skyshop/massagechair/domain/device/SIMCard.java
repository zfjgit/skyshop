/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SIMCard extends Device {

	private String sim;
	private int dataFlow;
	private Calendar dueDate;
	private SIMCardStatus status;
	private SIMCardOperator operator;

	protected SIMCard() {
	}

	public SIMCard(Long id, String description, int dataFlow, Calendar dueDate, String sim, SIMCardOperator simCardOperator, SIMCardStatus status, DeleteStatus deleteStatus) {
		super(id, null, description, deleteStatus);
		this.sim = sim;
		this.status = status;
		this.dueDate = dueDate;
		this.dataFlow = dataFlow;
		this.operator = simCardOperator;
	}

	public enum SIMCardStatus implements BaseEnum<SIMCardStatus, String> {
		USING("A", "使用中"), UNUSED("B", "未使用");

		private String code;
		private String name;

		private SIMCardStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public enum SIMCardOperator implements BaseEnum<SIMCardOperator, String> {

		CHINAL_TELECOM("A", "电信"), CHINAL_MOBILE("B", "移动"), CHINAL_UNICOM("C", "联通");

		private String code;
		private String name;

		private SIMCardOperator(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}
}
