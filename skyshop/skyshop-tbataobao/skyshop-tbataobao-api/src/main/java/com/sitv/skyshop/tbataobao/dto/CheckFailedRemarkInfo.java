/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import java.util.Calendar;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark.SubjectType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20 @ 2018年3月23日
 */
@Getter
@Setter
public class CheckFailedRemarkInfo extends FullInfoDto {

	private static final long serialVersionUID = 6188995650934929634L;

	private Long subjectId;
	
	private EnumInfo<SubjectType, Integer> subjectType;
	
	public CheckFailedRemarkInfo() {
	}
	
	public CheckFailedRemarkInfo(Long id, Long subjectId, EnumInfo<SubjectType, Integer> subjectType, String description, Calendar createTime) {
		super(id, description, createTime, null);
		this.subjectId = subjectId;
		this.subjectType = subjectType;
	}

	public static CheckFailedRemarkInfo create(CheckFailedRemark remark) {
		if(remark == null) {
			return null;
		}
		return new CheckFailedRemarkInfo(remark.getId(), remark.getSubjectId(), EnumInfo.valueOf(SubjectType.class, remark.getSubjectType().getCode()), remark.getDescription(), remark.getCreateTime());
	}
}
