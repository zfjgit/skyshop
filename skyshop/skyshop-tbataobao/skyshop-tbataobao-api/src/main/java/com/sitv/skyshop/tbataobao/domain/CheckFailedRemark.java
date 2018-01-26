/**
 *
 */
package com.sitv.skyshop.tbataobao.domain;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20 @ 2018年3月23日
 */
@Getter
@Setter
public class CheckFailedRemark extends DomainObject {

	private Long subjectId;
	
	private SubjectType subjectType;

	public CheckFailedRemark() {
	}

	public CheckFailedRemark(Long subjectId, SubjectType subjectType, String description, Calendar createTime) {
		this.subjectId = subjectId;
		this.subjectType = subjectType;
		setDescription(description);
		setCreateTime(createTime);
	}
	
	public enum SubjectType implements BaseEnum<SubjectType, Integer>{
		PRODUCT(1, "商品"), SHOP(2, "店铺");
		
		private Integer code;
		private String name;
		
		private SubjectType(Integer code, String name) {
			this.code = code;
			this.name = name;
		}

		public Integer getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
		
	}
}
