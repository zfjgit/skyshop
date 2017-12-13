/**
 *
 */
package com.sitv.skyshop.exception;

import com.sitv.skyshop.domain.ICheckCodeType;

/**
 * @author zfj20 @ 2017年12月9日
 */
public class CheckCodeVerificationFailedException extends RuntimeException {

	private static final long serialVersionUID = -3586210825186605049L;

	private ICheckCodeType checkCodeType;

	public CheckCodeVerificationFailedException(ICheckCodeType checkCodeType) {
		super("CHECKCODE校验失败：" + checkCodeType.getClass().getName() + "=" + checkCodeType.getCheckCode());
		this.checkCodeType = checkCodeType;
	}

	public ICheckCodeType getCheckCodeType() {
		return checkCodeType;
	}

}
