/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.IDeleteStatus;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class SIMCard extends Device implements IDeleteStatus {

	private String sim;
	private int dataFlow;
	private Calendar dueDate;
	private SIMCardStatus status;
	private SIMCardOperator operator;

	private DeleteStatus deleteStatus;

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public SIMCard(Long id, String name, String description) {
		super(id, name, description);
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param dataFlow
	 * @param dueDate
	 * @param sim
	 * @param simCardOperator
	 */
	public SIMCard(Long id, String description, int dataFlow, Calendar dueDate, String sim, SIMCardOperator simCardOperator, SIMCardStatus status) {
		this(id, null, description);
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

	/**
	 * @return the operator
	 */
	public SIMCardOperator getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(SIMCardOperator operator) {
		this.operator = operator;
	}

	/**
	 * @return the sim
	 */
	public String getSim() {
		return sim;
	}

	/**
	 * @param sim
	 *            the sim to set
	 */
	public void setSim(String sim) {
		this.sim = sim;
	}

	/**
	 * @return the dataFlow
	 */
	public int getDataFlow() {
		return dataFlow;
	}

	/**
	 * @param dataFlow
	 *            the dataFlow to set
	 */
	public void setDataFlow(int dataFlow) {
		this.dataFlow = dataFlow;
	}

	/**
	 * @return the dueDate
	 */
	public Calendar getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public SIMCardStatus getStatus() {
		return status;
	}

	public void setStatus(SIMCardStatus status) {
		this.status = status;
	}

	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
}
