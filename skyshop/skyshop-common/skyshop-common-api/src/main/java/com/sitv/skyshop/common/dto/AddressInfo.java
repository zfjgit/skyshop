/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.dto.info.FullInfoDto;

/**
 * @author zfj20
 * @version 2017年7月27日
 */
public class AddressInfo extends FullInfoDto {

	private static final long serialVersionUID = 1L;

	private Long parentId;
	private AddressInfo parent;

	protected AddressInfo() {
		super();
	}

	public AddressInfo(Long id, String name, AddressInfo parent) {
		super(id, name);
		this.parent = parent;
	}

	protected AddressInfo(Address address) {
		super(address.getId(), address.getName());
	}

	public static AddressInfo create(Address address) {
		if (address == null) {
			return null;
		}

		AddressInfo addressInfo = new AddressInfo(address);

		if (address.getParent() != null) {
			addressInfo.parent = new AddressInfo(address.getParent());
		} else {
			addressInfo.parentId = 0l;
		}

		return addressInfo;
	}

	public static List<AddressInfo> creates(List<Address> addresses) {
		List<AddressInfo> addressInfos = new ArrayList<>();

		if (addresses == null) {
			return addressInfos;
		}

		for (Address address : addresses) {
			addressInfos.add(create(address));
		}
		return addressInfos;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public AddressInfo getParent() {
		return parent;
	}

	public void setParent(AddressInfo parent) {
		this.parent = parent;
	}
}
