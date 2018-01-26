/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.SimpleInfoDto;
import com.sitv.skyshop.tbataobao.domain.ShopAddress;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class ShopAddressInfo extends SimpleInfoDto {

	private static final long serialVersionUID = -2695539562821602825L;

	private ShopAddressInfo parent;

	/**
	 *
	 */
	public ShopAddressInfo() {
	}

	public ShopAddressInfo(Long id, String name, ShopAddressInfo parent) {
		super(id, name);
		this.parent = parent;
	}

	public static ShopAddressInfo create(ShopAddress shopAddress) {
		if (shopAddress == null) {
			return null;
		}
		return new ShopAddressInfo(shopAddress.getId(), shopAddress.getName(), create(shopAddress.getParent()));
	}

	public static List<ShopAddressInfo> creates(List<ShopAddress> list) {
		List<ShopAddressInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}

		for (ShopAddress shopAddress : list) {
			if (shopAddress == null) {
				continue;
			}
			infos.add(create(shopAddress));
		}
		return infos;
	}
}
