/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device.malfunction;

import com.sitv.skyshop.massagechair.domain.device.SIMCard;
import com.sitv.skyshop.massagechair.domain.device.malfunction.SIMCardMalfunction;
import com.sitv.skyshop.massagechair.dto.device.SIMCardInfo;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class SIMCardMalfunctionInfo extends MalfunctionInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -7176361108727919117L;

	public String getType() {
		return SIMCardMalfunction.class.getSimpleName();
	}

	public static SIMCardMalfunctionInfo create(SIMCardMalfunction malfunction) {
		return new SIMCardMalfunctionInfo(malfunction);
	}

	public SIMCardMalfunctionInfo(SIMCardMalfunction malfunction) {
		super(malfunction);
		setDevice(SIMCardInfo.create((SIMCard) malfunction.getDevice()));
	}
}
