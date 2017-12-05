package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;

public class Address extends DomainObject {

	private Address parent;

	private int level;

	protected Address() {
	}

	private Address(Long id) {
		this.setId(id);
	}

	public Address getRoot() {
		return new Address(0l);
	}

	public Address(String name) {
		this.setName(name);
	}

	public Address getParent() {
		return parent;
	}

	public void setParent(Address parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
