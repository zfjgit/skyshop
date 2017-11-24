/**
 *
 */
package com.sitv.skyshop.massagechair.domain.device;

import com.sitv.skyshop.common.domain.FullAddress;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class InstallationAddress extends FullAddress {

	private String contact;
	private String contactNumber;

	private String location;

	/**
	 * @param id
	 * @param description
	 * @param fullAddress
	 * @param location
	 * @param contact
	 * @param contactNumber
	 */
	public InstallationAddress(Long id, String description, String fullAddress, String location, String contact, String contactNumber) {
		super(id, description, fullAddress);
		this.contact = contact;
		this.contactNumber = contactNumber;
		this.location = location;
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

}
