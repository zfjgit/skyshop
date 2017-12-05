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

	private static final long serialVersionUID = -4372937544847496856L;

	public static SIMCardInfo create(SIMCard simCard) {
		if (simCard == null) {
			return null;
		}

		return new SIMCardInfo(simCard.getId(), simCard.getDescription(), simCard.getSim(), simCard.getDataFlow(), simCard.getDueDate(), simCard.getOperator().getCode(),
		                simCard.getStatus().getCode(), simCard.getCreateTime(), simCard.getUpdateTime());
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

	@NotNull
	private String operator;

	@NotBlank
	private String sim;

	@NotBlank
	@DecimalMin("1")
	private int dataFlow;

	private Calendar dueDate;

	private String status;

	public SIMCardInfo(Long id, String description, String sim, int dataFlow, Calendar dueDate, String operator, String status, Calendar createTime, Calendar updateTime) {
		super(id, null, description, createTime, updateTime);
		this.dataFlow = dataFlow;
		this.dueDate = dueDate;
		this.operator = operator;
		this.sim = sim;
		this.setStatus(status);
	}

	/**
	 * @return the dataFlow
	 */
	public int getDataFlow() {
		return dataFlow;
	}

	/**
	 * @return the dueDate
	 */
	public Calendar getDueDate() {
		return dueDate;
	}

	public String getOperator() {
		return operator;
	}

	/**
	 * @return the sim
	 */
	public String getSim() {
		return sim;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * @param dataFlow
	 *            the dataFlow to set
	 */
	public void setDataFlow(int dataFlow) {
		this.dataFlow = dataFlow;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @param sim
	 *            the sim to set
	 */
	public void setSim(String sim) {
		this.sim = sim;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
