/**
 * 
 */
package com.sitv.skyshop.dto.info;

import com.sitv.skyshop.dto.Dto;
import com.sitv.skyshop.dto.ValueInfoDto;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
public abstract class RelationInfoDto extends ValueInfoDto {

	private static final long serialVersionUID = -4510736264020430488L;

	private Dto dto1;
	
	private Dto dto2;
	
	public RelationInfoDto(Dto dto1, Dto dto2) {
		super();
		this.dto1 = dto1;
		this.dto2 = dto2;
	}

	public Dto getDto1() {
		return dto1;
	}

	public void setDto1(Dto dto1) {
		this.dto1 = dto1;
	}

	public Dto getDto2() {
		return dto2;
	}

	public void setDto2(Dto dto2) {
		this.dto2 = dto2;
	}
	
}
