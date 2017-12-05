/**
 *
 */
package com.sitv.skyshop.common.exception;

/**
 * @author zfj20 @ 2017年12月7日
 */
public class ResourceNotFoundException extends NotFoundException {

	/**
	 *
	 */
	private static final long serialVersionUID = -3666256675413897040L;

	public ResourceNotFoundException() {

	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
