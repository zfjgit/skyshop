/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;

/**
 * @author zfj20 @ 2017年11月15日
 */
public enum SIMCardOperator implements BaseEnum<ChairStatus, String> {

	CHINAL_TELECOM("A", "电信"), CHINAL_MOBILE("B", "移动"), CHINAL_UNICOM("C", "联通");

	private String code;
	private String name;

	private SIMCardOperator(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
