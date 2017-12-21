/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.device.SIMCard;
import com.sitv.skyshop.massagechair.domain.device.SIMCard.SIMCardOperator;
import com.sitv.skyshop.massagechair.domain.device.SIMCard.SIMCardStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SIMCardInfo extends DeviceInfo {

	private static final long serialVersionUID = -4372937544847496856L;

	private EnumInfo<SIMCardOperator, String> operator;

	private String sim;

	private int dataFlow;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar dueDate;

	private EnumInfo<SIMCardStatus, String> status;

	private boolean needRecharge;

	public SIMCardInfo() {
	}

	public SIMCardInfo(Long id, String description, String sim, int dataFlow, Calendar dueDate, EnumInfo<SIMCardOperator, String> operator, EnumInfo<SIMCardStatus, String> status,
	                Calendar createTime, Calendar updateTime, EnumInfo<DeleteStatus, Integer> deleteStatus) {
		super(id, sim, description, createTime, updateTime, deleteStatus);
		this.dataFlow = dataFlow;
		this.dueDate = dueDate;
		this.operator = operator;
		this.sim = sim;
		this.setStatus(status);
	}

	public static SIMCardInfo create(SIMCard simCard) {
		if (simCard == null) {
			return null;
		}

		return new SIMCardInfo(simCard.getId(), simCard.getDescription(), simCard.getSim(), simCard.getDataFlow(), simCard.getDueDate(), new EnumInfo<>(simCard.getOperator()),
		                new EnumInfo<>(simCard.getStatus()), simCard.getCreateTime(), simCard.getUpdateTime(), new EnumInfo<>(simCard.getDeleteStatus()));
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
