package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
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

	public FullAddress(Long id, Address province, Address city, Address district, String detailAddress) {
		super(id);
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddress = detailAddress;
		this.fullAddress = province.getName() + city.getName() + (district != null ? district.getName() : "") + detailAddress;
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

}
