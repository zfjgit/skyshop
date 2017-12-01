/**
 *
 */
package com.sitv.skyshop.dto;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class ValueInfoDto extends Dto {

	private static final long serialVersionUID = 8643864180652469826L;

	private Long id;

	private EnumInfo<DeleteStatus, Integer> deleteStatus;

	private String checkCode;

	public ValueInfoDto() {
	}

	public ValueInfoDto(Long id) {
		this.id = id;
	}

}
