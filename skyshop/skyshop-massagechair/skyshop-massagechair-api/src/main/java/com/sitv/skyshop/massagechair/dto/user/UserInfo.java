/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.user.Manager;
import com.sitv.skyshop.massagechair.domain.user.User;

/**
 * @author zfj20 @ 2017年11月15日
 */
public abstract class UserInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 5140119276101189640L;
	private String password;
	private String email;
	private String mobile;

	private String status;

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param description
	 * @param createTime
	 * @param updateTime
	 * @param password
	 * @param email
	 * @param mobile
	 * @param status
	 */
	public UserInfo(Long id, String code, String name, String description, Calendar createTime, Calendar updateTime, String password, String email, String mobile, String status) {
		super(id, code, name, description, createTime, updateTime);
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.status = status;
	}

	/**
	 *
	 */
	public UserInfo() {
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

	public abstract String getType();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <I extends UserInfo, T extends User> I create(T t) {
		if (t != null) {
			if (t instanceof Manager) {
				return (I) ManagerInfo.create((Manager) t);
			}
		}
		return null;
	}

	/**
	 * @param list
	 * @return
	 */
	public static <I extends UserInfo, T extends User> List<I> creates(List<T> list) {
		List<I> infos = new ArrayList<>();
		if (list != null) {
			for (T user : list) {
				infos.add(create(user));
			}
		}
		return infos;
	}
}
