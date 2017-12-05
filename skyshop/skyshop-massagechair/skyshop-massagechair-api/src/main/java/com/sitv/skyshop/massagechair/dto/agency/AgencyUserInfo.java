/**
 *
 */
package com.sitv.skyshop.massagechair.dto.agency;

import com.sitv.skyshop.massagechair.domain.agency.AgencyUser;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class AgencyUserInfo extends UserInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 882606278440717391L;

	private String agencyUserStatus;

	private String level;

	private AgencyInfo agency;

	public AgencyUserInfo(Long id, String code, String name, String password, String agencyUserStatus, String level, AgencyInfo agency) {
		super(id, code, name, password, agencyUserStatus);
		this.level = level;
		this.agency = agency;
	}

	public static AgencyUserInfo create(AgencyUser agencyUser) {
		if (agencyUser == null) {
			return null;
		}
		AgencyInfo agency = AgencyInfo.create(agencyUser.getAgency());

		return new AgencyUserInfo(agencyUser.getId(), agencyUser.getCode(), agencyUser.getName(), agencyUser.getPassword(), agencyUser.getAgencyUserStatus().getCode(),
		                agencyUser.getLevel().getCode(), agency);
	}

	public String getAgencyUserStatus() {
		return agencyUserStatus;
	}

	public void setAgencyUserStatus(String agencyUserStatus) {
		this.agencyUserStatus = agencyUserStatus;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public AgencyInfo getAgency() {
		return agency;
	}

	public void setAgency(AgencyInfo agency) {
		this.agency = agency;
	}

	public String getType() {
		return getClass().getSimpleName();
	}

}
