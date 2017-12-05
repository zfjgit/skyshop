/**
 *
 */
package com.sitv.skyshop.massagechair.domain.agency;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.massagechair.domain.user.User;

/**
 * @author zfj20 @ 2017年12月4日
 */
public class AgencyUser extends User {

	private AgencyUserStatus agencyUserStatus;

	private AgencyUserLevel level;

	private Agency agency;

	private String checkCode;

	public AgencyUser(Long id, String code, String name, String password, AgencyUserStatus agencyUserStatus, AgencyUserLevel level, Agency agency) {
		super(id, code, name, password);
		this.agencyUserStatus = agencyUserStatus;
		this.level = level;
		this.agency = agency;

		this.checkCode = calcCheckCode();
	}

	public AgencyUserLevel getLevel() {
		return level;
	}

	public void setLevel(AgencyUserLevel level) {
		this.level = level;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public enum AgencyUserStatus implements BaseEnum<AgencyUserStatus, String> {
		UNUSED("A", "未启用"), NORMAL("B", "正常"), CLOSED("C", "关闭");

		private String code;
		private String name;

		private AgencyUserStatus(String code, String name) {
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

	public enum AgencyUserLevel implements BaseEnum<AgencyUserLevel, String> {
		SYSTEM("A", "平台"), AGENCY("B", "区域总代");

		private String code;
		private String name;

		private AgencyUserLevel(String code, String name) {
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

	public String getType() {
		return AgencyUser.class.getSimpleName();
	}

	public AgencyUserStatus getAgencyUserStatus() {
		return agencyUserStatus;
	}

	public void setAgencyUserStatus(AgencyUserStatus agencyUserStatus) {
		this.agencyUserStatus = agencyUserStatus;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String calcCheckCode() {
		return "";
	}

}
