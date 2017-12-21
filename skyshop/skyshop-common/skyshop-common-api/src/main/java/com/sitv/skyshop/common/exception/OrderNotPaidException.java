/**
 *
 */
package com.sitv.skyshop.common.exception;

/**
 * @author zfj20 @ 2017年12月28日
 */
public class OrderNotPaidException extends RuntimeException {

	private static final long serialVersionUID = -8292858469983129135L;

	public OrderNotPaidException(String msg) {
		super(msg);
	}
}
