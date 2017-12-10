/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.io.Serializable;

import com.sitv.skyshop.dto.Dto;

/**
 * @author zfj20 @ 2017年12月9日
 */
public interface UserSessionInfo extends Serializable {

	String getToken();

	long getLastAccessTime();

	Dto getUserInfo();

}
