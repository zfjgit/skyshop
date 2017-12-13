/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.price.PriceInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MassageChairInfo extends DeviceInfo {

	private static final long serialVersionUID = -5318115230379761101L;

	private GSMModuleInfo gsmModule;

	private InstallationAddressInfo installationAddress;

	private List<PriceInfo> prices;

	// 品牌、厂商
	private String brand;

	private AgencyInfo agency;

	private EnumInfo<ChairStatus, String> status;

	private String qrcode;

	public MassageChairInfo() {
	}

	public MassageChairInfo(Long id, String name, String brand, String description, Calendar createTime, Calendar updateTime, EnumInfo<ChairStatus, String> status,
	                InstallationAddressInfo installationAddressInfo, GSMModuleInfo gsmModuleInfo, List<PriceInfo> prices, AgencyInfo agency,
	                EnumInfo<DeleteStatus, Integer> deleteStatus, String qrcode) {
		super(id, name, description, createTime, updateTime, deleteStatus);
		this.gsmModule = gsmModuleInfo;
		this.brand = brand;
		this.prices = prices;
		this.qrcode = qrcode;
		this.setAgency(agency);
		this.setStatus(status);
		this.installationAddress = installationAddressInfo;
	}

	public static MassageChairInfo create(MassageChair massageChair) {
		if (massageChair == null) {
			return null;
		}

		InstallationAddressInfo installationAddressInfo = InstallationAddressInfo.create(massageChair.getInstallationAddress());
		GSMModuleInfo gsmModuleInfo = GSMModuleInfo.create(massageChair.getGsmModule());

		List<PriceInfo> prices = PriceInfo.creates(massageChair.getPrices());

		AgencyInfo agency = AgencyInfo.create(massageChair.getAgency());

		return new MassageChairInfo(massageChair.getId(), massageChair.getName(), massageChair.getBrand(), massageChair.getDescription(), massageChair.getCreateTime(),
		                massageChair.getUpdateTime(), new EnumInfo<>(massageChair.getStatus()), installationAddressInfo, gsmModuleInfo, prices, agency,
		                new EnumInfo<>(massageChair.getDeleteStatus()), massageChair.getQrcode());
	}

	public static List<MassageChairInfo> creates(List<MassageChair> list) {
		List<MassageChairInfo> massageChairInfos = new ArrayList<>();
		if (list != null) {
			for (MassageChair massageChair : list) {
				massageChairInfos.add(create(massageChair));
			}
		}
		return massageChairInfos;
	}

}
