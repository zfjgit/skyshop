/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.domain.Industry;
import com.sitv.skyshop.dto.info.SimpleInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class IndustryInfo extends SimpleInfoDto {

	private static final long serialVersionUID = 7397205295370487768L;

	public IndustryInfo(Long id) {
		super(id);
	}

	public IndustryInfo(Long id, String code, String name) {
		super(id, code, name);
	}

	public static IndustryInfo create(Industry industry) {
		if (industry == null) {
			return null;
		}

		return new IndustryInfo(industry.getId(), industry.getCode(), industry.getName());
	}

	public static List<IndustryInfo> creates(List<Industry> industries) {
		List<IndustryInfo> industryInfos = new ArrayList<>();

		if (industries == null) {
			return industryInfos;
		}

		for (Industry industry : industries) {
			industryInfos.add(create(industry));
		}

		return industryInfos;
	}

}
