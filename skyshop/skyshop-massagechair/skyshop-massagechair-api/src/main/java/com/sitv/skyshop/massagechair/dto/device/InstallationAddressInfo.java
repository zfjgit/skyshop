/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.dto.info.SimpleInfoDto;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月16日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstallationAddressInfo extends FullInfoDto {

	private static final long serialVersionUID = -1280255241361323611L;

	private String fullAddress;
	private String location;
	private String contact;
	private String contactNumber;

	private AddressInfo province;
	private AddressInfo city;
	private AddressInfo district;
	private String detailAddress;

	private List<SimpleInfoDto> chairs;

	private AgencyInfo agency;

	public InstallationAddressInfo() {
	}

	public InstallationAddressInfo(Long id, String description, AddressInfo province, AddressInfo city, AddressInfo district, String detailAddress, AgencyInfo agency,
	                String location, String contact, String contactNumber, Calendar createTime, Calendar updateTime, EnumInfo<DeleteStatus, Integer> deleteStatus,
	                List<SimpleInfoDto> chairs) {
		super(id, description, createTime, updateTime);

		this.location = location;
		this.contact = contact;
		this.contactNumber = contactNumber;
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddress = detailAddress;
		this.agency = agency;
		this.chairs = chairs;
		setDeleteStatus(deleteStatus);
		this.fullAddress = province.getName() + city.getName() + (district == null ? "" : district.getName()) + detailAddress;
	}

	public static InstallationAddressInfo create(InstallationAddress addr) {
		if (addr == null) {
			return null;
		}
		AddressInfo province = AddressInfo.create(addr.getProvince());
		AddressInfo city = AddressInfo.create(addr.getCity());
		AddressInfo district = AddressInfo.create(addr.getDistrict());
		AgencyInfo agencyInfo = AgencyInfo.create(addr.getAgency());

		List<SimpleInfoDto> chairInfos = new ArrayList<>();
		for (MassageChair c : addr.getChairs()) {
			chairInfos.add(new SimpleInfoDto(c.getId(), c.getName()));
		}
		return new InstallationAddressInfo(addr.getId(), addr.getDescription(), province, city, district, addr.getDetailAddress(), agencyInfo, addr.getLocation(),
		                addr.getContact(), addr.getContactNumber(), addr.getCreateTime(), addr.getUpdateTime(), new EnumInfo<>(addr.getDeleteStatus()), chairInfos);
	}

	public static List<InstallationAddressInfo> creates(List<InstallationAddress> addrs) {
		List<InstallationAddressInfo> addressInfos = new ArrayList<>();
		if (addrs != null) {
			for (InstallationAddress installationAddress : addrs) {
				addressInfos.add(create(installationAddress));
			}
		}
		return addressInfos;
	}

}
