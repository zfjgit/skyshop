package com.sitv.skyshop.domain;

import java.util.Calendar;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class DomainObject extends SimpleType {

	private Long id;

	private String name;

	private String code;

	private int serialNumber;

	private int version;

	private String description;

	private Calendar createTime;

	private Calendar updateTime;

	protected DomainObject() {
		super();
	}

	public DomainObject(String name, String code) {
		this();
		this.name = name;
		this.code = code;
	}

	public DomainObject(String name, String code, int serialNumber, int version, String description, Calendar createTime) {
		this(name, code);
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
		this(id, name);
		this.description = description;
	}

	/**
	 * @param id
	 * @param name
	 */
	public DomainObject(Long id, String name) {
		this(id);
		this.name = name;
	}

	/**
	 * @param id
	 */
	public DomainObject(Long id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param description
	 */
	public DomainObject(Long id, String code, String name, String description) {
		this(id, name);
		this.code = code;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
