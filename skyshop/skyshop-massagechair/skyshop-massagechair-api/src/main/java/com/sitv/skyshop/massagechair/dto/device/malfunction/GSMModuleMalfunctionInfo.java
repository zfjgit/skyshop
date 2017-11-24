/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device.malfunction;

import com.sitv.skyshop.massagechair.domain.device.GSMModule;
import com.sitv.skyshop.massagechair.domain.device.malfunction.GSMModuleMalfunction;
import com.sitv.skyshop.massagechair.dto.device.GSMModuleInfo;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class GSMModuleMalfunctionInfo extends MalfunctionInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -7176361108727919117L;

	/**
	 * @param malfunction
	 */
	public static GSMModuleMalfunctionInfo create(GSMModuleMalfunction malfunction) {
		return new GSMModuleMalfunctionInfo(malfunction);
	}

	public GSMModuleMalfunctionInfo(GSMModuleMalfunction malfunction) {
		super(malfunction);
		setDevice(GSMModuleInfo.create((GSMModule) malfunction.getDevice()));
	}

	public String getType() {
		return GSMModuleMalfunction.class.getSimpleName();
	}

}
