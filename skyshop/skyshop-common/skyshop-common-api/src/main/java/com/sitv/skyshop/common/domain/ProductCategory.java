/**
 *
 */
package com.sitv.skyshop.common.domain;

import com.sitv.skyshop.domain.SimpleType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class ProductCategory extends SimpleType {

	private ProductCategory parent;

	private int level;

	private Long shopId;

	/**
	 *
	 */
	public ProductCategory() {
	}

	public ProductCategory(Long id, String name, ProductCategory parent) {
		super(id, name);
		this.parent = parent;
	}
}
