/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.Calendar;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class DeviceInfo extends FullInfoDto {

	private static final long serialVersionUID = -9205823954941200866L;

	public DeviceInfo() {
	}

	public DeviceInfo(Long id, String name, String description, Calendar createTime, Calendar updateTime, EnumInfo<DeleteStatus, Integer> deleteStatus) {
		super(id, name, description, createTime, updateTime);
		setDeleteStatus(deleteStatus);
	}

}
