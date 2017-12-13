/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import java.util.Calendar;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.ICheckCodeType;
import com.sitv.skyshop.domain.IDeleteStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class User extends DomainObject implements ICheckCodeType, IDeleteStatus {

	private String password;
	private String email;
	private String mobile;
	private UserStatus status;
	private UserType type;
	private String checkCode;
	private DeleteStatus deleteStatus;
	private String loginCheckCode;

	public User(String code, String name, String description, String password, String email, String mobile, UserStatus status, UserType type, DeleteStatus deleteStatus) {
		super(name, code);
		setDescription(description);
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.status = status;
		this.type = type;
		this.deleteStatus = deleteStatus;

		calcCheckCode();
		calcLoginCheckCode();
	}

	public User(Long id, String code, String name, String description, String password, String email, String mobile, UserStatus status, UserType type, DeleteStatus deleteStatus) {
		super(name, code);
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.status = status;
		this.type = type;
		this.deleteStatus = deleteStatus;
		setDescription(description);
		setCreateTime(Calendar.getInstance());
		setUpdateTime(Calendar.getInstance());
		calcCheckCode();
		calcLoginCheckCode();
	}

	protected User() {
	}

	public User(Long id, String code, String name, String password) {
		super(id, code, name, null);
		this.password = password;
	}

	public enum UserType implements BaseEnum<UserType, String> {
		SYSTEM("A", "平台"), AGENCY("B", "区域总代");

		private String code;
		private String name;

		private UserType(String code, String name) {
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

	public enum UserStatus implements BaseEnum<UserStatus, String> {
		UNUSED("A", "未启用"), NORMAL("B", "正常"), CLOSED("C", "关闭");

		private String code;
		private String name;

		private UserStatus(String code, String name) {
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

	public String calcCheckCode() {
		setCheckCode("");
		return "";
	}

	public String calcLoginCheckCode() {
		setLoginCheckCode(Utils.UUID());
		return getLoginCheckCode();
	}
}
