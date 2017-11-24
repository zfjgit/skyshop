/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device.malfunction;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.device.malfunction.GSMModuleMalfunction;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction;
import com.sitv.skyshop.massagechair.domain.device.malfunction.MassageChairMalfunction;
import com.sitv.skyshop.massagechair.domain.device.malfunction.SIMCardMalfunction;
import com.sitv.skyshop.massagechair.dto.device.DeviceInfo;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class MalfunctionInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -3609147158554661001L;

	private DeviceInfo device;

	private String status;

	/**
	 *
	 */
	public MalfunctionInfo() {
	}

	public MalfunctionInfo(Malfunction malfunction) {
		super(malfunction.getId(), malfunction.getName(), malfunction.getDescription(), malfunction.getCreateTime(), malfunction.getUpdateTime());
		this.status = malfunction.getStatus().getCode();
	}

	/**
	 * @return the device
	 */
	public DeviceInfo getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(DeviceInfo device) {
		this.device = device;
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

	public abstract String getType();

	/**
	 * @param malfunction
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <I extends MalfunctionInfo> I create(Malfunction malfunction) {
		if (malfunction != null) {
			if (malfunction instanceof GSMModuleMalfunction) {
				return (I) GSMModuleMalfunctionInfo.create((GSMModuleMalfunction) malfunction);
			} else if (malfunction instanceof SIMCardMalfunction) {
				return (I) SIMCardMalfunctionInfo.create((SIMCardMalfunction) malfunction);
			} else if (malfunction instanceof MassageChairMalfunction) {
				return (I) MassageChairMalfunctionInfo.create((MassageChairMalfunction) malfunction);
			}
		}
		return null;
	}

	public static <I extends MalfunctionInfo, T extends Malfunction> List<I> creates(List<T> malfunctions) {
		List<I> list = new ArrayList<>();
		if (malfunctions != null) {
			for (Malfunction malfunction : malfunctions) {
				list.add(create(malfunction));
			}
		}
		return list;
	}
}
