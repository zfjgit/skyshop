/**
 *
 */
package com.sitv.skyshop.common.utils.sender;

/**
 * @author zfj20 @ 2017年12月14日
 */
public abstract class SMSSender {

	public abstract void send(String mobile, String content);

	public abstract void sendOpen(String mobile, int minutes, Long orderId);

	public abstract void sendClose(String mobile, Long orderId);

	public abstract void sendCheck(String mobile);

	public abstract void asyncSendCheck(String mobile);

	public abstract void sendResetURL(String mobile, String url);

}
