/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.IDeleteStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class Device extends DomainObject implements IDeleteStatus {

	private DeleteStatus deleteStatus;

	protected Device() {
	}

	public Device(Long id, String name, String description, DeleteStatus deleteStatus) {
		super(id, name, description);
		this.deleteStatus = deleteStatus;
	}

}
