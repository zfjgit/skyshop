/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import java.util.Calendar;

import com.sitv.skyshop.massagechair.domain.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyUser extends User {

	private Agency agency;

	protected AgencyUser() {
	}

	public AgencyUser(Long id, String code, String name, String password, UserStatus status, UserType type, Agency agency, DeleteStatus deleteStatus) {
		super(id, code, name, "", password, "", "", status, type, deleteStatus);
		this.agency = agency;
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
		calcCheckCode();
	}

	public String getCheckCode() {
		return "";
	}

	public String calcCheckCode() {
		setCheckCode("");
		return "";
	}

	public UserType getType() {
		return UserType.AGENCY;
	}
}
