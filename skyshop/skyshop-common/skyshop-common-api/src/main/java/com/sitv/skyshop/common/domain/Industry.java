package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.SimpleType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Industry extends SimpleType {

	protected Industry() {
	}

	public Industry(String name, String code) {
		super(name, code);
	}
}
