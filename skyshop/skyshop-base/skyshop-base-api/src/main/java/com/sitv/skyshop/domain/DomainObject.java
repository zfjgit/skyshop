package com.sitv.skyshop.domain;

import java.util.Calendar;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class DomainObject extends SimpleType {

	private int serialNumber;

	private int version;

	private String description;

	private Calendar createTime;

	private Calendar updateTime;

	protected DomainObject() {
	}

	public DomainObject(String name, String code) {
		super(name, code);
	}

	public DomainObject(String name, String code, int serialNumber, int version, String description, Calendar createTime) {
		super(name, code);
		this.serialNumber = serialNumber;
		this.version = version;
		this.description = description;
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public DomainObject(Long id, String name, String description) {
		super(id, name, null);
		this.description = description;
	}

	/**
	 * @param id
	 * @param name
	 */
	public DomainObject(Long id, String name) {
		super(id, name, null);
	}

	/**
	 * @param id
	 */
	public DomainObject(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param description
	 */
	public DomainObject(Long id, String code, String name, String description) {
		super(id, name, code);
		this.description = description;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public enum DeleteStatus implements BaseEnum<DeleteStatus, Integer> {
		DELETED(1, "已删除"), NORMAL(0, "未删除");

		private int code;
		private String name;

		DeleteStatus(int code, String name) {
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
