/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class Device extends DomainObject {

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public Device(Long id, String name, String description) {
		super(id, name, description);
	}

}
