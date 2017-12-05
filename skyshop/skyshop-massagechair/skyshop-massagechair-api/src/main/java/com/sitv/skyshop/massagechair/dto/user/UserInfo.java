/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.agency.AgencyUser;
import com.sitv.skyshop.massagechair.domain.agency.SystemUser;
import com.sitv.skyshop.massagechair.domain.user.Manager;
import com.sitv.skyshop.massagechair.domain.user.User;
import com.sitv.skyshop.massagechair.dto.agency.AgencyUserInfo;
import com.sitv.skyshop.massagechair.dto.agency.SystemUserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(exclude = "password", callSuper = true)
public class UserInfo extends FullInfoDto {

	private static final long serialVersionUID = 5140119276101189640L;

	@JsonIgnore
	private String password;
	private String email;
	private String mobile;

	private EnumInfo<User.UserStatus, String> status;
	private EnumInfo<User.UserType, String> type;

	public UserInfo() {
	}

	public UserInfo(Long id, String code, String name, String password, EnumInfo<User.UserStatus, String> status, EnumInfo<User.UserType, String> type,
	                EnumInfo<DeleteStatus, Integer> deleteStatus, String checkCode, Calendar createTime, Calendar updateTime) {
		super(id, name, null, createTime, updateTime);
		setCode(code);
		this.type = type;
		this.status = status;
		this.password = password;
		setCheckCode(checkCode);
		setDeleteStatus(deleteStatus);
	}

	public UserInfo(Long id, String code, String name, String description, Calendar createTime, Calendar updateTime, String password, String email, String mobile,
	                EnumInfo<User.UserStatus, String> status, EnumInfo<User.UserType, String> type, EnumInfo<DeleteStatus, Integer> deleteStatus, String checkCode) {
		super(id, code, name, description, createTime, updateTime);
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.status = status;
		this.type = type;
		setCheckCode(checkCode);
		setDeleteStatus(deleteStatus);
	}

	@SuppressWarnings("unchecked")
	public static <I extends UserInfo, T extends User> I create(T t) {
		if (t != null) {
			if (t instanceof Manager) {
				return (I) ManagerInfo.create((Manager) t);
			} else if (t instanceof AgencyUser) {
				return (I) AgencyUserInfo.create((AgencyUser) t);
			} else if (t instanceof SystemUser) {
				return (I) SystemUserInfo.create((SystemUser) t);
			}
		}
		return null;
	}

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
