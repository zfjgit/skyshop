/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import java.util.List;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class Role extends DomainObject {

	private boolean isSuper;

	private List<Permission> permissions;

	/**
	 *
	 */
	public Role() {
	}

	/**
	 * @param code
	 * @param name
	 * @param super
	 */
	public Role(String code, String name, boolean issuper) {
		super(name, code);
		this.isSuper = issuper;
	}

	/**
	 * @return the isSuper
	 */
	public boolean isSuper() {
		return isSuper;
	}

	/**
	 * @param isSuper
	 *            the isSuper to set
	 */
	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
