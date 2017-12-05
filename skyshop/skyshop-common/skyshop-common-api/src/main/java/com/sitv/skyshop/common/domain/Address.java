package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
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

}
