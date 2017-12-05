/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.Calendar;

import com.sitv.skyshop.dto.info.FullInfoDto;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class DeviceInfo extends FullInfoDto {

	private static final long serialVersionUID = -9205823954941200866L;

	public DeviceInfo() {
	}

	public DeviceInfo(Long id, String name, String description, Calendar createTime, Calendar updateTime) {
		super(id, name, description, createTime, updateTime);
	}

}
