/**
 *
 */
package com.sitv.skyshop.common.exception;

/**
 * @author zfj20 @ 2017年12月7日
 */
public abstract class StatusException extends RuntimeException {

	private static final long serialVersionUID = -1443211136743532489L;

	public StatusException() {
	}

	public StatusException(String message) {
		super(message);
	}
}
