/**
 *
 */
package com.sitv.skyshop.dto.info;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public abstract class FullInfoDto extends ValueInfoDto {

	private static final long serialVersionUID = -5898891878119679794L;

	private String name;

	private String code;

	private int serialNumber;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Calendar createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Calendar updateTime;

	private String description;

	public FullInfoDto() {
	}

	public FullInfoDto(Long id, String name) {
		super(id);
		this.name = name;
	}

	public FullInfoDto(String name) {
		super();
		this.name = name;
	}

	public FullInfoDto(Long id, String name, String desc) {
		this(id, name);
		this.description = desc;
	}

	public FullInfoDto(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public FullInfoDto(Long id, String desc, Calendar createTime, Calendar updateTime) {
		super(id);
		this.description = desc;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public FullInfoDto(Long id, String name, String desc, Calendar createTime, Calendar updateTime) {
		super(id);
		this.name = name;
		this.description = desc;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public FullInfoDto(Long id, String code, String name, String description, Calendar createTime, Calendar updateTime) {
		super(id);
		this.code = code;
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public FullInfoDto(Long id, Calendar createTime, Calendar updateTime) {
		super(id);
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

}
