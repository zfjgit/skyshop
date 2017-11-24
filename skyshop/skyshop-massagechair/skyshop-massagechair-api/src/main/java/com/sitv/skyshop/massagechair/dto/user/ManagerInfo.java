/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.Calendar;

import com.sitv.skyshop.massagechair.domain.user.Manager;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class ManagerInfo extends UserInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 4694279511853382858L;

	/**
	 *
	 */
	public ManagerInfo() {
		super();
	}

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param description
	 * @param password
	 * @param email
	 * @param mobile
	 * @param createTime
	 * @param updateTime
	 * @param status
	 */
	public ManagerInfo(Long id, String code, String name, String description, String password, String email, String mobile, Calendar createTime, Calendar updateTime,
	                String status) {
		super(id, code, name, description, createTime, updateTime, password, email, mobile, status);
	}

	/**
	 * @param manager
	 * @return
	 */
	public static ManagerInfo create(Manager m) {
		if (m == null) {
			return null;
		}
		return new ManagerInfo(m.getId(), m.getCode(), m.getName(), m.getDescription(), m.getPassword(), m.getEmail(), m.getMobile(), m.getCreateTime(), m.getUpdateTime(),
		                m.getStatus().getCode());
	}

	public String getType() {
		return Manager.class.getSimpleName();
	}
}
