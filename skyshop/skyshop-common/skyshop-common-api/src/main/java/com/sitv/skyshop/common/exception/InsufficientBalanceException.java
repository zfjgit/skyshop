/**
 *
 */
package com.sitv.skyshop.common.exception;

/**
 * @author zfj20 @ 2018年1月26日
 */
public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = -4014253742166336949L;

	public InsufficientBalanceException(String msg) {
		super(msg);
	}
}
