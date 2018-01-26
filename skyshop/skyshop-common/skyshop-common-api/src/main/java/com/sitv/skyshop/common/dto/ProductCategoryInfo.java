/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.domain.ProductCategory;
import com.sitv.skyshop.dto.info.SimpleInfoDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class ProductCategoryInfo extends SimpleInfoDto {

	private ProductCategoryInfo parent;

	private int level;

	/**
	 *
	 */
	private static final long serialVersionUID = 2622883922819273394L;

	/**
	 *
	 */
	public ProductCategoryInfo() {
	}

	public ProductCategoryInfo(Long id, String name, ProductCategoryInfo parent) {
		super(id, name);
		this.parent = parent;
	}

	public static ProductCategoryInfo create(ProductCategory category) {
		if (category == null) {
			return null;
		}
		ProductCategoryInfo parent = null;
		if (category.getParent() != null) {
			parent = ProductCategoryInfo.create(category.getParent());
		}
		return new ProductCategoryInfo(category.getId(), category.getName(), parent);
	}

	public static List<ProductCategoryInfo> creates(List<ProductCategory> children) {
		List<ProductCategoryInfo> infos = new ArrayList<>();
		if (children == null) {
			return infos;
		}
		for (ProductCategory sysCategory : children) {
			if (sysCategory == null) {
				continue;
			}
			infos.add(ProductCategoryInfo.create(sysCategory));
		}
		return infos;
	}
}
