/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class SIMCardOperator extends DomainObject {

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param description
	 */
	public SIMCardOperator(Long id, String code, String name, String description) {
		super(id, code, name, description);
	}

}
