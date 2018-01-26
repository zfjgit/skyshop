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
public class SysCategory extends SimpleType {

	private SysCategory parent;

	private int level;

	/**
	 *
	 */
	public SysCategory() {
	}

	public SysCategory(Long id, String name) {
		super(id, name);
	}
}
