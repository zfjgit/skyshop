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

	public static final int UNNORMAL_STATUS_CODE = 601;
	public static final int DISCONNECTED_CODE = 602;
	public static final int NO_PRICE_CODE = 603;

	public MassageChairResponseInfo(int code, String message) {
		super(code, message);
	}

	public static <T> MassageChairResponseInfo<T> UNNORMAL_STATUS(String message) {
		return new MassageChairResponseInfo<>(UNNORMAL_STATUS_CODE, message);
	}

	public static <T> MassageChairResponseInfo<T> DISCONNECTED(String message) {
		return new MassageChairResponseInfo<>(DISCONNECTED_CODE, message);
	}

	public static <T> MassageChairResponseInfo<T> NO_PRICE(String message) {
		return new MassageChairResponseInfo<>(NO_PRICE_CODE, message);
	}
}
