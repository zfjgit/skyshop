/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class SIMCard extends Device {

	private SIMCardOperator operator;
	private String sim;
	private int dataFlow;
	private Calendar dueDate;

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
	public SIMCard(Long id, String name, String description, int dataFlow, Calendar dueDate, String sim, SIMCardOperator simCardOperator) {
		this(id, name, description);
		this.dataFlow = dataFlow;
		this.dueDate = dueDate;
		this.sim = sim;
		this.operator = simCardOperator;
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
}
