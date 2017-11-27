/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device.malfunction;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.massagechair.domain.device.Device;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class Malfunction extends DomainObject {

	private Device device;

	private MalfunctionStatus status;

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public Malfunction(Long id, String name, String description) {
		super(id, name, description);
	}

	/**
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * @return the status
	 */
	public MalfunctionStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(MalfunctionStatus status) {
		this.status = status;
	}

	public enum MalfunctionStatus implements BaseEnum<MalfunctionStatus, String> {
		NEW("NEW", "待处理"), PROCESSING("PROCESSING", "处理中"), PROCESSED("PROCESSED", "已处理");

		private String code;
		private String name;

		private MalfunctionStatus(String code, String name) {
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

	public abstract String getType();

}
