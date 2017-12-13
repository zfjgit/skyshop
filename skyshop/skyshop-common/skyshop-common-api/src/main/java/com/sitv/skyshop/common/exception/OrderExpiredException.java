/**
 *
 */
package com.sitv.skyshop.common.exception;

/**
 * @author zfj20 @ 2017年12月17日
 */
public class OrderExpiredException extends RuntimeException {

	/**
	 * @param string
	 */
	public OrderExpiredException(String msg) {
		super(msg);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -1136810478200402175L;

}
