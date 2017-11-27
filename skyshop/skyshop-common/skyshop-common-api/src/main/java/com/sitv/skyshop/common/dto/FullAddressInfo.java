/**
 * 
 */
package com.sitv.skyshop.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.domain.FullAddress;
import com.sitv.skyshop.dto.info.SimpleInfoDto;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
public class FullAddressInfo extends SimpleInfoDto {

	private static final long serialVersionUID = 3724254906736167574L;

	private AddressInfo province;
	private AddressInfo city;
	private AddressInfo district;
	private String detailAddress;
	
	private String fullAddress;
	
	private Long provinceId;
	private Long cityId;
	private Long districtId;
	
	public FullAddressInfo(Long id) {
		super(id);
	}
	
	public static FullAddressInfo create(FullAddress fullAddress) {
		if(fullAddress == null) {
			return null;
		}
		
		FullAddressInfo fullAddressInfo = new FullAddressInfo(fullAddress.getId());
		if(fullAddress.getProvince() != null) {
			AddressInfo province = AddressInfo.create(fullAddress.getProvince());
			fullAddressInfo.setProvince(province);
			fullAddressInfo.setProvinceId(province.getId());
		}
		if(fullAddress.getCity() != null) {
			AddressInfo city = AddressInfo.create(fullAddress.getCity());
			fullAddressInfo.setCity(city);
			fullAddressInfo.setCityId(city.getId());
		}
		if(fullAddress.getDistrict() != null) {
			AddressInfo district = AddressInfo.create(fullAddress.getDistrict());
			fullAddressInfo.setDistrict(district);
			fullAddressInfo.setDistrictId(district.getId());
		}
		
		fullAddressInfo.setDetailAddress(fullAddress.getDetailAddress());
		fullAddressInfo.setFullAddress(fullAddress.getFullAddress());
		
		return fullAddressInfo;
	}
	
	public static List<FullAddressInfo> creates(List<FullAddress> fullAddresses) {
		List<FullAddressInfo> fullAddressInfos = new ArrayList<>();
		if(fullAddresses == null) {
			return fullAddressInfos;
		}
		
		for (FullAddress fullAddress : fullAddresses) {
			fullAddressInfos.add(create(fullAddress));
		}
		
		return fullAddressInfos;
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

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
}
