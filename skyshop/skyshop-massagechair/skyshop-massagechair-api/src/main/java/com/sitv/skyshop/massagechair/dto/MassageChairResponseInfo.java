/**
 *
 */
package com.sitv.skyshop.massagechair.dto;

import com.sitv.skyshop.dto.ResponseInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月1日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MassageChairResponseInfo<T> extends ResponseInfo<T> {

	private static final long serialVersionUID = -3301863435666756202L;

	public MassageChairResponseInfo(int code, String message) {
		super(code, message);
	}

}
