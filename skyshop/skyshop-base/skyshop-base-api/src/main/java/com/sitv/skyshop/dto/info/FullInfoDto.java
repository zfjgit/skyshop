/**
 *
 */
package com.sitv.skyshop.dto.info;

import java.util.Calendar;

import com.sitv.skyshop.dto.ValueInfoDto;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
public abstract class FullInfoDto extends ValueInfoDto {

	private static final long serialVersionUID = -5898891878119679794L;

	private String name;

	private String code;

	private int serialNumber;

	private Calendar createTime;

	private Calendar updateTime;

	private String description;

	protected FullInfoDto() {
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

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param description
	 * @param createTime
	 * @param updateTime
	 */
	public FullInfoDto(Long id, String code, String name, String description, Calendar createTime, Calendar updateTime) {
		super(id);
		this.code = code;
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/**
	 * @param id
	 * @param createTime2
	 * @param updateTime2
	 */
	public FullInfoDto(Long id, Calendar createTime, Calendar updateTime) {
		super(id);
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
