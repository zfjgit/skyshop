/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sitv.skyshop.common.dto.UserSessionInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年12月7日
 */
@Getter
@Setter
@ToString()
@JsonIgnoreProperties({ "createTime", "updateTime", "id", "deleteStatus", "name", "serialNumber", "status", "type", "code", "description", "checkCode", "email", "mobile" })
public class LoginUserInfo extends UserInfo implements UserSessionInfo {

	private static final long serialVersionUID = 145155310888788641L;

	private String ip;
	private String token;
	private String sessionId;
	private UserInfo userInfo;
	private long lastAccessTime;

	public LoginUserInfo() {
	}

	public LoginUserInfo(String ip, UserInfo userInfo, String token, long lastAccessTime) {
		super();
		this.ip = ip;
		this.userInfo = userInfo;
		this.token = token;
		this.lastAccessTime = lastAccessTime;
	}

}
