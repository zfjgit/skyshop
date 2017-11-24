/**
 *
 */
package com.sitv.skyshop.massagechair.dto.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;

/**
 * @author zfj20 @ 2017年11月16日
 */
public class InstallationAddressInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = -1280255241361323611L;
	private String fullAddress;
	private String location;
	private String contact;
	private String contactNumber;

	/**
	 * @param id
	 * @param description
	 * @param fullAddress
	 * @param location
	 * @param contact
	 * @param contactNumber
	 * @param createTime
	 */
	public InstallationAddressInfo(Long id, String description, String fullAddress, String location, String contact, String contactNumber, Calendar createTime,
	                Calendar updateTime) {
		super(id, description, createTime, updateTime);

		this.fullAddress = fullAddress;
		this.location = location;
		this.contact = contact;
		this.contactNumber = contactNumber;
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

	/**
	 * @param installationAddress
	 * @return
	 */
	public static InstallationAddressInfo create(InstallationAddress addr) {
		return new InstallationAddressInfo(addr.getId(), addr.getDescription(), addr.getFullAddress(), addr.getLocation(), addr.getContact(), addr.getContactNumber(),
		                addr.getCreateTime(), addr.getUpdateTime());
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
