/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Manager extends User {

	private List<Role> roles;

	protected Manager() {
	}

	public Manager(String code, String name, String description, String password, String email, String mobile, UserStatus status, UserType type, DeleteStatus deleteStatus) {
		super(code, name, description, password, email, mobile, status, type, deleteStatus);
	}

}
