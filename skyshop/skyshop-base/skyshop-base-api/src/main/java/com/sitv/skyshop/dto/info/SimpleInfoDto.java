/**
 *
 */
package com.sitv.skyshop.dto.info;

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
public class SimpleInfoDto extends ValueInfoDto {

	private static final long serialVersionUID = -8925202536322986374L;

	private String code;
	private String name;

	public SimpleInfoDto() {
	}

	public SimpleInfoDto(Long id) {
		super(id);
	}

	public SimpleInfoDto(Long id, String code, String name) {
		this(id);
		this.name = name;
		this.code = code;
	}

	public SimpleInfoDto(Long id, String name) {
		this(id);
		this.name = name;
	}

}
