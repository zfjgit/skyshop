/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.Calendar;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.device.Device.DeviceStatus;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class DeviceInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -9205823954941200866L;

	private String status;

	/**
	 *
	 */
	public DeviceInfo() {
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param status
	 * @param createTime
	 * @param updateTime
	 */
	public DeviceInfo(Long id, String name, String description, DeviceStatus status, Calendar createTime, Calendar updateTime) {
		super(id, name, description, createTime, updateTime);
		if (status != null) {
			this.status = status.getCode();
		}
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
