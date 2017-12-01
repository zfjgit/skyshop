package com.sitv.skyshop.domain;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
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

	public DomainObject(Long id, String name, String description) {
		super(id, name, null);
		this.description = description;
	}

	public DomainObject(Long id, String name) {
		super(id, name, null);
	}

	public DomainObject(Long id) {
		super(id);
	}

	public DomainObject(Long id, String code, String name, String description) {
		super(id, name, code);
		this.description = description;
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
