/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.device.GSMModule;
import com.sitv.skyshop.massagechair.domain.device.GSMModule.GSMModuleStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class GSMModuleInfo extends DeviceInfo {

	private static final long serialVersionUID = 1510842333944062983L;

	@NotBlank
	private String imei;

	@NotNull
	private SIMCardInfo simCard;

	private String module;

	private EnumInfo<GSMModuleStatus, String> status;

	public GSMModuleInfo(Long id, String description, String module, String imei, SIMCardInfo sim, EnumInfo<GSMModuleStatus, String> status, Calendar createTime,
	                Calendar updateTime, EnumInfo<DeleteStatus, Integer> deleteStatus) {
		super(id, null, description, createTime, updateTime, deleteStatus);
		this.imei = imei;
		this.module = module;
		this.simCard = sim;
		this.status = status;
	}

	public static GSMModuleInfo create(GSMModule gsmModule) {
		if (gsmModule == null) {
			return null;
		}
		SIMCardInfo sim = SIMCardInfo.create(gsmModule.getSimCard());
		return new GSMModuleInfo(gsmModule.getId(), gsmModule.getDescription(), gsmModule.getModule(), gsmModule.getImei(), sim, new EnumInfo<>(gsmModule.getStatus()),
		                gsmModule.getCreateTime(), gsmModule.getUpdateTime(), new EnumInfo<>(gsmModule.getDeleteStatus()));
	}

	public static List<GSMModuleInfo> creates(List<GSMModule> list) {
		List<GSMModuleInfo> gsmModuleInfos = new ArrayList<>();
		if (list != null) {
			for (GSMModule gsmModule : list) {
				gsmModuleInfos.add(create(gsmModule));
			}
		}
		return gsmModuleInfos;
	}

}
