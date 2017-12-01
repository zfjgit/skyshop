/**
 *
 */
package com.sitv.skyshop.dto.info;

import com.sitv.skyshop.dto.Dto;
import com.sitv.skyshop.dto.ValueInfoDto;

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
public abstract class RelationInfoDto extends ValueInfoDto {

	private static final long serialVersionUID = -4510736264020430488L;

	private Dto dto1;

	private Dto dto2;

	public RelationInfoDto(Dto dto1, Dto dto2) {
		super();
		this.dto1 = dto1;
		this.dto2 = dto2;
	}

}
