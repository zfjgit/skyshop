/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import com.sitv.skyshop.dto.info.SimpleInfoDto;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class UserRoleInfo extends SimpleInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 4444853286189390068L;

	private RoleInfo role;
	private ManagerInfo manager;

	/**
	 * @param id
	 */
	public UserRoleInfo(Long id) {
		super(id);
	}

	/**
	 *
	 */
	public UserRoleInfo() {
		this(null);
	}

	/**
	 * @return the role
	 */
	public RoleInfo getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(RoleInfo role) {
		this.role = role;
	}

	/**
	 * @return the manager
	 */
	public ManagerInfo getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(ManagerInfo manager) {
		this.manager = manager;
	}
}
