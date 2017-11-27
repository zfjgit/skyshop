/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.sitv.skyshop.massagechair.domain.device.SIMCard;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class SIMCardInfo extends DeviceInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -4372937544847496856L;

	@NotNull
	private SIMCardOperatorInfo operator;

	@NotBlank
	private String sim;

	@NotBlank
	@DecimalMin("1")
	private int dataFlow;

	private Calendar dueDate;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param sim
	 * @param dataFlow
	 * @param dueDate
	 * @param createTime
	 * @param updateTime
	 */
	public SIMCardInfo(Long id, String name, String description, String sim, int dataFlow, Calendar dueDate, SIMCardOperatorInfo operatorInfo, Calendar createTime,
	                Calendar updateTime) {
		super(id, name, description, null, createTime, updateTime);
		this.dataFlow = dataFlow;
		this.dueDate = dueDate;
		this.operator = operatorInfo;
		this.sim = sim;
	}

	/**
	 * @return the operator
	 */
	public SIMCardOperatorInfo getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(SIMCardOperatorInfo operator) {
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

	/**
	 * @param simCard
	 * @return
	 */
	public static SIMCardInfo create(SIMCard simCard) {
		if (simCard == null) {
			return null;
		}

		SIMCardOperatorInfo operatorInfo = SIMCardOperatorInfo.create(simCard.getOperator());
		return new SIMCardInfo(simCard.getId(), simCard.getName(), simCard.getDescription(), simCard.getSim(), simCard.getDataFlow(), simCard.getDueDate(), operatorInfo,
		                simCard.getCreateTime(), simCard.getUpdateTime());
	}

	public static List<SIMCardInfo> creates(List<SIMCard> list) {
		List<SIMCardInfo> simCardInfos = new ArrayList<>();
		if (list != null) {
			for (SIMCard simCard : list) {
				simCardInfos.add(create(simCard));
			}
		}
		return simCardInfos;
	}
}
