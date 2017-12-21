package com.sitv.skyshop.massagechair.dto.record;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperateResultInfo extends UseRecordInfo {

	private static final long serialVersionUID = 2147666083019726993L;

	public OperateResultInfo() {
	}

	public OperateResultInfo(String code, String name) {
		super();
		setCode(code);
		setName(name);
	}

	public String toString() {
		return "code=" + this.getCode() + "/name=" + this.getName();
	}
}
