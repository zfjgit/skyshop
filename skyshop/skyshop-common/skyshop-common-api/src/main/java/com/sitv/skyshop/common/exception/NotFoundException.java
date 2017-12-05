/**
 *
 */
package com.sitv.skyshop.common.exception;

/**
 * @author zfj20 @ 2017年12月7日
 */
public abstract class NotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 6282570148792332747L;

	public NotFoundException() {

	}

	public NotFoundException(String msg) {
		super(msg);
	}
}
