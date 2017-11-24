package com.sitv.skyshop.common.dto;

import com.sitv.skyshop.dto.ResponseInfo;

public class CommonResponseInfo<T> extends ResponseInfo<T> {

	private static final long serialVersionUID = -4777073009963427603L;

	protected CommonResponseInfo(int code) {
		super(code);
	}
	
}
