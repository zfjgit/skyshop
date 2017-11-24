/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device.malfunction;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class MassageChairMalfunction extends Malfunction {

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public MassageChairMalfunction(Long id, String name, String description) {
		super(id, name, description);
	}

	public String getType() {
		return MassageChairMalfunction.class.getSimpleName();
	}
}
