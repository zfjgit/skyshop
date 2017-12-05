/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class UserRole extends DomainObject {

	private Role role;
	private User user;

	protected UserRole() {
	}

	public UserRole(User user, Role role) {
		this.role = role;
		this.setUser(user);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
