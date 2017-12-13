/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import java.util.Calendar;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.agency.AgencyUser;
import com.sitv.skyshop.massagechair.domain.user.User;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月4日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AgencyUserInfo extends UserInfo {

	private static final long serialVersionUID = 882606278440717391L;

	private AgencyInfo agency;

	public AgencyUserInfo() {
	}

	public AgencyUserInfo(Long id, String code, String name, String password, EnumInfo<User.UserStatus, String> status, EnumInfo<User.UserType, String> type, AgencyInfo agency,
	                EnumInfo<DeleteStatus, Integer> deleteStatus, String checkCode, String loginCheckCode, Calendar createTime, Calendar updateTime) {
		super(id, code, name, password, status, type, deleteStatus, checkCode, loginCheckCode, createTime, updateTime);
		this.agency = agency;
	}

	public static AgencyUserInfo create(AgencyUser agencyUser) {
		if (agencyUser == null) {
			return null;
		}
		AgencyInfo agency = AgencyInfo.create(agencyUser.getAgency());

		return new AgencyUserInfo(agencyUser.getId(), agencyUser.getCode(), agencyUser.getName(), "*", new EnumInfo<>(agencyUser.getStatus()), new EnumInfo<>(agencyUser.getType()),
		                agency, new EnumInfo<>(agencyUser.getDeleteStatus()), agencyUser.getCheckCode(), agencyUser.getLoginCheckCode(), agencyUser.getCreateTime(),
		                agencyUser.getUpdateTime());
	}

}
