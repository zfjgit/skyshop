/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.SimpleInfoDto;
import com.sitv.skyshop.tbataobao.domain.ShopIndustry;

/**
 * @author zfj20
 */
public class ShopIndustryInfo extends SimpleInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 5028295446770049923L;

	/**
	 *
	 */
	public ShopIndustryInfo() {
	}

	/**
	 * @param id
	 * @param name
	 */
	public ShopIndustryInfo(Long id, String name) {
		super(id, name);
	}

	public static ShopIndustryInfo create(ShopIndustry industry) {
		if (industry == null) {
			return null;
		}
		return new ShopIndustryInfo(industry.getId(), industry.getName());
	}

	public static List<ShopIndustryInfo> creates(List<ShopIndustry> list) {
		List<ShopIndustryInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (ShopIndustry industry : list) {
			if (industry == null) {
				continue;
			}
			infos.add(create(industry));
		}
		return infos;
	}
}
