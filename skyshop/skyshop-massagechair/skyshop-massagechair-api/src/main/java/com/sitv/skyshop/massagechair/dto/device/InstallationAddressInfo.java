/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;

/**
 * @author zfj20 @ 2017年11月16日
 */
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

	private AgencyInfo agency;

	public InstallationAddressInfo(Long id, String description, AddressInfo province, AddressInfo city, AddressInfo district, String detailAddress, AgencyInfo agency,
	                String location, String contact, String contactNumber, Calendar createTime, Calendar updateTime) {
		super(id, description, createTime, updateTime);

		this.location = location;
		this.contact = contact;
		this.contactNumber = contactNumber;
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddress = detailAddress;
		this.agency = agency;
		this.fullAddress = province.getName() + city.getName() + (district == null ? "" : district.getName()) + detailAddress;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public static InstallationAddressInfo create(InstallationAddress addr) {
		if (addr == null) {
			return null;
		}
		AddressInfo province = AddressInfo.create(addr.getProvince());
		AddressInfo city = AddressInfo.create(addr.getCity());
		AddressInfo district = AddressInfo.create(addr.getDistrict());
		AgencyInfo agencyInfo = AgencyInfo.create(addr.getAgency());
		return new InstallationAddressInfo(addr.getId(), addr.getDescription(), province, city, district, addr.getDetailAddress(), agencyInfo, addr.getLocation(),
		                addr.getContact(), addr.getContactNumber(), addr.getCreateTime(), addr.getUpdateTime());
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

	public AddressInfo getProvince() {
		return province;
	}

	public void setProvince(AddressInfo province) {
		this.province = province;
	}

	public AddressInfo getCity() {
		return city;
	}

	public void setCity(AddressInfo city) {
		this.city = city;
	}

	public AddressInfo getDistrict() {
		return district;
	}

	public void setDistrict(AddressInfo district) {
		this.district = district;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public AgencyInfo getAgency() {
		return agency;
	}

	public void setAgency(AgencyInfo agency) {
		this.agency = agency;
	}
}
