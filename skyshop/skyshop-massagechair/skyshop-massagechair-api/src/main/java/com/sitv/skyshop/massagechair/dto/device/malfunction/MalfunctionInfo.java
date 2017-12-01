/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device.malfunction;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionStatus;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionType;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MalfunctionInfo extends FullInfoDto {

	private static final long serialVersionUID = -3609147158554661001L;

	private MassageChairInfo chair;

	private EnumInfo<MalfunctionStatus, String> status;

	private EnumInfo<MalfunctionType, String> type;

	public MalfunctionInfo() {
	}

	public MalfunctionInfo(Malfunction malfunction) {
		super(malfunction.getId(), malfunction.getDescription(), malfunction.getCreateTime(), malfunction.getUpdateTime());
		this.status = new EnumInfo<>(malfunction.getStatus());
		this.type = new EnumInfo<>(malfunction.getType());
		this.chair = MassageChairInfo.create(malfunction.getChair());
	}

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

}
