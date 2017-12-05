/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class RolePermission extends DomainObject {

	private Role role;
	private Permission permission;

	protected RolePermission() {
	}

	/**
	 * @param role
	 * @param permission
	 */
	public RolePermission(Role role, Permission permission) {
		this.role = role;
		this.permission = permission;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
