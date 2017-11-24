/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class Device extends DomainObject {

	private DeviceStatus status;

	public enum DeviceStatus implements BaseEnum<DeviceStatus, String> {
		ENABLED("ENABLED", "启用"), DISABLED("DISABLED", "停用"), FAULT("FAULT", "故障");

		private String code;
		private String name;

		private DeviceStatus(String code, String name) {
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

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public Device(Long id, String name, String description) {
		super(id, name, description);
	}

	/**
	 * @return the status
	 */
	public DeviceStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(DeviceStatus status) {
		this.status = status;
	}
}
