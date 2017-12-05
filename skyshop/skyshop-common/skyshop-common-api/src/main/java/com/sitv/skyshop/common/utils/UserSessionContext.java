/**
 *
 */
package com.sitv.skyshop.common.utils;

import com.sitv.skyshop.common.dto.UserSessionInfo;

/**
 * @author zfj20 @ 2017年12月9日
 */
public class UserSessionContext {

	private static ThreadLocal<UserSessionInfo> context = new ThreadLocal<>();

	public static void setSession(UserSessionInfo session) {
		context.set(session);
	}

	public static UserSessionInfo getSession() {
		return context.get();
	}
}
