/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device.malfunction;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class MalfunctionInfo extends FullInfoDto {

	private static final long serialVersionUID = -3609147158554661001L;

	private MassageChairInfo chair;

	private String status;

	private String type;

	public MalfunctionInfo() {
	}

	public MalfunctionInfo(Malfunction malfunction) {
		super(malfunction.getId(), malfunction.getDescription(), malfunction.getCreateTime(), malfunction.getUpdateTime());
		this.status = malfunction.getStatus().getCode();
		this.type = malfunction.getType().getCode();
		this.chair = MassageChairInfo.create(malfunction.getChair());
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

	/**
	 * @param malfunction
	 * @return
	 */
	public static MalfunctionInfo create(Malfunction malfunction) {
		if (malfunction == null) {
			return null;
		}
		return new MalfunctionInfo(malfunction);
	}

	public static List<MalfunctionInfo> creates(List<Malfunction> malfunctions) {
		List<MalfunctionInfo> list = new ArrayList<>();
		if (malfunctions != null) {
			for (Malfunction malfunction : malfunctions) {
				list.add(create(malfunction));
			}
		}
		return list;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MassageChairInfo getChair() {
		return chair;
	}

	public void setChair(MassageChairInfo chair) {
		this.chair = chair;
	}
}
