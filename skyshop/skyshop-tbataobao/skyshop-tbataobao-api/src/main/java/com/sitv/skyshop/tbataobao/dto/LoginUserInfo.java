/**
 *
 */
package com.sitv.skyshop.tbataobao.dto;

import com.sitv.skyshop.common.dto.UserSessionInfo;
import com.sitv.skyshop.dto.Dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Getter
@Setter
public class LoginUserInfo extends Dto implements UserSessionInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = -13802097006751350L;

	private String ip;
	private String token;
	private String sessionId;
	private SysCheckerInfo userInfo;
	private long lastAccessTime;

	/**
	 *
	 */
	public LoginUserInfo() {
	}

	public String getToken() {
		return token;
	}

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public Dto getUserInfo() {
		return userInfo;
	}

	public String getSessionId() {
		return sessionId;
	}

	public LoginUserInfo(String ip, String token, String sessionId, SysCheckerInfo userInfo, long lastAccessTime) {
		super();
		this.ip = ip;
		this.token = token;
		this.sessionId = sessionId;
		this.userInfo = userInfo;
		this.lastAccessTime = lastAccessTime;
	}

}
