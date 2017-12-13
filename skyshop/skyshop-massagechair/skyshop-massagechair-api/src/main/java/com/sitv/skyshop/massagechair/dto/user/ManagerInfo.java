/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.Calendar;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.user.Manager;
import com.sitv.skyshop.massagechair.domain.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ManagerInfo extends UserInfo {

	private static final long serialVersionUID = 4694279511853382858L;

	public ManagerInfo() {
		super();
	}

	public ManagerInfo(Long id, String code, String name, String description, String password, String email, String mobile, Calendar createTime, Calendar updateTime,
	                EnumInfo<User.UserStatus, String> status, EnumInfo<User.UserType, String> type, EnumInfo<DeleteStatus, Integer> deleteStatus, String checkCode,
	                String loginCheckCode) {
		super(id, code, name, description, createTime, updateTime, password, email, mobile, status, type, deleteStatus, checkCode, loginCheckCode);
	}

	public static ManagerInfo create(Manager m) {
		if (m == null) {
			return null;
		}
		return new ManagerInfo(m.getId(), m.getCode(), m.getName(), m.getDescription(), m.getPassword(), m.getEmail(), m.getMobile(), m.getCreateTime(), m.getUpdateTime(),
		                new EnumInfo<>(m.getStatus()), new EnumInfo<>(m.getType()), new EnumInfo<>(m.getDeleteStatus()), m.getCheckCode(), m.getLoginCheckCode());
	}

}
