/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import java.util.Calendar;

import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.common.domain.FullAddress;
import com.sitv.skyshop.domain.IDeleteStatus;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class InstallationAddress extends FullAddress implements IDeleteStatus {

	private String contact;
	private String contactNumber;

	private String location;

	private Agency agency;

	private DeleteStatus deleteStatus;

	/**
	 * @param id
	 * @param description
	 * @param fullAddress
	 * @param location
	 * @param contact
	 * @param contactNumber
	 */
	public InstallationAddress(Long id, Agency agency, Address province, Address city, Address district, String detailAddress, String description, String location, String contact,
	                String contactNumber, Calendar createTime, Calendar updateTime) {
		super(id, province, city, district, detailAddress);
		this.contact = contact;
		this.contactNumber = contactNumber;
		this.location = location;
		this.agency = agency;
		setDescription(description);
		setCreateTime(createTime);
		setUpdateTime(updateTime);
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

}
