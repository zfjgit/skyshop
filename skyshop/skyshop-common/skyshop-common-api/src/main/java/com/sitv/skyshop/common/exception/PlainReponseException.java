/**
 *
 */
package com.sitv.skyshop.common.exception;

/**
 * @author zfj20 @ 2017年12月6日
 */
public class PlainReponseException extends RuntimeException {
	private static final long serialVersionUID = -3386434827128032595L;

	public PlainReponseException(String message) {
		super(message);
	}

	public PlainReponseException(String message, Throwable e) {
		super(message, e);
	}
}
