package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;

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

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Configuration getParent() {
		return parent;
	}

	public void setParent(Configuration parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
}
