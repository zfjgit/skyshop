/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.domain.SysCategory;
import com.sitv.skyshop.dto.info.SimpleInfoDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class SysCategoryInfo extends SimpleInfoDto {

	private SysCategoryInfo parent;

	private int level;

	/**
	 *
	 */
	private static final long serialVersionUID = 2622883922819273394L;

	/**
	 *
	 */
	public SysCategoryInfo() {
	}

	public SysCategoryInfo(Long id, String name, SysCategoryInfo parent) {
		super(id, name);
		this.parent = parent;
	}

	public static SysCategoryInfo create(SysCategory category) {
		if (category == null) {
			return null;
		}
		SysCategoryInfo parent = null;
		if (category.getParent() != null) {
			parent = SysCategoryInfo.create(category.getParent());
		}
		return new SysCategoryInfo(category.getId(), category.getName(), parent);
	}

	public static List<SysCategoryInfo> creates(List<SysCategory> children) {
		List<SysCategoryInfo> infos = new ArrayList<>();
		if (children == null) {
			return infos;
		}
		for (SysCategory sysCategory : children) {
			if (sysCategory == null) {
				continue;
			}
			infos.add(SysCategoryInfo.create(sysCategory));
		}
		return infos;
	}
}
