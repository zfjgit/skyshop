/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import java.util.List;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class Manager extends User {

	private List<Role> roles;

	/**
	 * @param code
	 * @param name
	 * @param description
	 * @param password
	 * @param email
	 * @param mobile
	 * @param status
	 */
	public Manager(String code, String name, String description, String password, String email, String mobile, String status) {
		super(code, name, description, password, email, mobile, status);
	}

	/**
	 *
	 */
	public Manager() {
		super();
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getType() {
		return getClass().getSimpleName();
	}
}
