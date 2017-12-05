/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class User extends DomainObject {

	private String password;
	private String email;
	private String mobile;
	private UserStatus status;

	/**
	 * @param code
	 * @param name
	 * @param description
	 * @param status
	 * @param mobile
	 * @param email
	 * @param password
	 */
	public User(String code, String name, String description, String password, String email, String mobile, String status) {
		super(name, code);
		setDescription(description);
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.status = UserStatus.valueOf(status);
	}

	/**
	 *
	 */
	public User() {
	}

	/**
	 * @param code
	 * @param name
	 * @param password
	 */
	public User(Long id, String code, String name, String password) {
		super(id, code, name, null);
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public enum UserStatus implements BaseEnum<UserStatus, String> {
		ENABLED("ENABLED", ""), DISABLED("DISABLED", "");

		private String code;
		private String name;

		private UserStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

	}

	public abstract String getType();
}
