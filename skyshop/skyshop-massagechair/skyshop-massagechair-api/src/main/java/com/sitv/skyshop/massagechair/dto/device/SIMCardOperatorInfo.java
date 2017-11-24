/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.device.SIMCardOperator;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class SIMCardOperatorInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 7112330238223833239L;

	/**
	 *
	 */
	public SIMCardOperatorInfo() {
	}

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param description
	 * @param createTime
	 * @param updateTime
	 */
	public SIMCardOperatorInfo(Long id, String code, String name, String description, Calendar createTime, Calendar updateTime) {
		super(id, code, name, description, createTime, updateTime);
	}

	@NotNull
	public String getName() {
		return super.getName();
	}

	/**
	 * @param operator
	 * @return
	 */
	public static SIMCardOperatorInfo create(SIMCardOperator operator) {
		if (operator == null) {
			return null;
		}
		return new SIMCardOperatorInfo(operator.getId(), operator.getCode(), operator.getName(), operator.getDescription(), operator.getCreateTime(), operator.getUpdateTime());
	}

	public static List<SIMCardOperatorInfo> creates(List<SIMCardOperator> list) {
		List<SIMCardOperatorInfo> simCardOperatorInfos = new ArrayList<>();
		if (list != null) {
			for (SIMCardOperator simCardOperator : list) {
				simCardOperatorInfos.add(create(simCardOperator));
			}
		}
		return simCardOperatorInfos;
	}
}
