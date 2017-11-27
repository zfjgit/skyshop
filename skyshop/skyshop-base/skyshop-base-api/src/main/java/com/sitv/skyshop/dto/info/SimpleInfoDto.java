/**
 * 
 */
package com.sitv.skyshop.dto.info;

import com.sitv.skyshop.dto.ValueInfoDto;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
public abstract class SimpleInfoDto extends ValueInfoDto {

	private static final long serialVersionUID = -8925202536322986374L;

	private String code;
	private String name;
	
	public SimpleInfoDto(Long id) {
		super(id);
	}
	
	public SimpleInfoDto(Long id, String code, String name) {
		super(id);
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
