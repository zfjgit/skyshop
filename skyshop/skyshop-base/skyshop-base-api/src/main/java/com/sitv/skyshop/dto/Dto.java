package com.sitv.skyshop.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class Dto implements Serializable {

	private static final long serialVersionUID = 8037302744329410455L;

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
