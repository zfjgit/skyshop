package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;

public class FullAddress extends DomainObject {

	private Address province;
	private Address city;
	private Address district;
	private String detailAddress;

	private String fullAddress;

	protected FullAddress() {
	}

	public FullAddress(Address province, Address city, Address district, String detailAddress, String fullAddress) {
		super();
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddress = detailAddress;
		this.fullAddress = fullAddress;
	}

	/**
	 * @param id
	 * @param description
	 * @param fullAddress
	 */
	public FullAddress(Long id, String description, String fullAddress) {
		super(id, null, description);
		this.fullAddress = fullAddress;
	}

	public Address getProvince() {
		return province;
	}

	public void setProvince(Address province) {
		this.province = province;
	}

	public Address getCity() {
		return city;
	}

	public void setCity(Address city) {
		this.city = city;
	}

	public Address getDistrict() {
		return district;
	}

	public void setDistrict(Address district) {
		this.district = district;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
}
