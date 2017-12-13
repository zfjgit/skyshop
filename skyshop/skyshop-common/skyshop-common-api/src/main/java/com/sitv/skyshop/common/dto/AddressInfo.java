/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 * @version 2017年7月27日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AddressInfo extends FullInfoDto {

	private static final long serialVersionUID = 1L;

	private Long parentId;
	private AddressInfo parent;
	private int level;

	public AddressInfo() {
		super();
	}

	public AddressInfo(Long id, String code, String name, int level, Address parent) {
		super(id, name, code);
		this.level = level;
		this.parent = create(parent);
		this.parentId = parent == null ? 0l : parent.getId();
	}

	protected AddressInfo(Address address) {
		this(address.getId(), address.getCode(), address.getName(), address.getLevel(), address.getParent());
	}

	public static AddressInfo create(Address address) {
		if (address == null) {
			return null;
		}

		AddressInfo addressInfo = new AddressInfo(address);

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

}
