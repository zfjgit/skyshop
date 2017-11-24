/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device.malfunction;

import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.malfunction.MassageChairMalfunction;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class MassageChairMalfunctionInfo extends MalfunctionInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -7176361108727919117L;

	public String getType() {
		return MassageChairMalfunction.class.getSimpleName();
	}

	public static MassageChairMalfunctionInfo create(MassageChairMalfunction malfunction) {
		return new MassageChairMalfunctionInfo(malfunction);
	}

	public MassageChairMalfunctionInfo(MassageChairMalfunction malfunction) {
		super(malfunction);
		setDevice(MassageChairInfo.create((MassageChair) malfunction.getDevice()));
	}
}
