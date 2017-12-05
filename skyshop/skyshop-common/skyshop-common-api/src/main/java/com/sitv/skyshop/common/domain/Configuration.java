package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Configuration extends DomainObject {

	private int level;

	private String imgurl;

	private Configuration parent;

	private String classification;

	protected Configuration() {
	}

	private Configuration(Long id) {
		setId(id);
	}

	public Configuration(String name, String classification, int level, int serialNumber) {
		setName(name);
		setClassification(classification);
		setLevel(level);
		setSerialNumber(serialNumber);
	}

	public Configuration getRoot() {
		return new Configuration(0l);
	}

}
