/**
 *
 */
package com.sitv.skyshop.domain;

/**
 * @author zfj20 @ 2017年12月4日
 */
public interface ICheckCodeType {

	String calcCheckCode();

	String getCheckCode();

	void setCheckCode(String checkCode);

	default boolean verifyCheckCode() {
		return getCheckCode() != null && getCheckCode().equals(calcCheckCode());
	}
}
