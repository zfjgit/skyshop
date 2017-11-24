package com.sitv.skyshop.user.domain;

import com.sitv.skyshop.common.domain.FullAddress;
import com.sitv.skyshop.domain.DomainObject;

public class Receiver extends DomainObject {

	private FullAddress fullAddress;
	private String mobile;
	private String zipcode;
	private boolean isDefault;
	
	protected Receiver() {
	}
	
	public FullAddress getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(FullAddress fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
