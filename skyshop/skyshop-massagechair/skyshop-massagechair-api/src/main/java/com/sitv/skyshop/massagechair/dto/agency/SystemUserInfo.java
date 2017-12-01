/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.util.Calendar;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.agency.SystemUser;
import com.sitv.skyshop.massagechair.domain.user.User;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月8日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SystemUserInfo extends UserInfo {

	private static final long serialVersionUID = 6301961093278687007L;

	public SystemUserInfo(Long id, String code, String name, String pwd, EnumInfo<User.UserStatus, String> status, EnumInfo<User.UserType, String> type,
	                EnumInfo<DeleteStatus, Integer> deleteStatus, String checkCode, Calendar createTime, Calendar updateTime) {
		super(id, code, name, pwd, status, type, deleteStatus, checkCode, createTime, updateTime);
	}

	public static SystemUserInfo create(SystemUser systemUser) {
		if (systemUser == null) {
			return null;
		}

		return new SystemUserInfo(systemUser.getId(), systemUser.getCode(), systemUser.getName(), "*", new EnumInfo<>(systemUser.getStatus()), new EnumInfo<>(systemUser.getType()),
		                new EnumInfo<>(systemUser.getDeleteStatus()), systemUser.getCheckCode(), systemUser.getCreateTime(), systemUser.getUpdateTime());
	}
}
