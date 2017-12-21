/**
 *
 */
package com.sitv.skyshop.common.utils.sender;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.httpclient4.HttpConnectionManager;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月14日
 */
@Slf4j
@Component
public class YunPianSMSSender extends SMSSender {

	@Autowired
	private HttpConnectionManager connetionManager;

	@Autowired
	private Environment env;

	public void send(String mobile, String content) {
		log.debug("发送短信");
		log.debug("mobile=" + mobile);
		log.debug("content=" + content);
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(content)) {
			log.warn("手机号码或短信内容为空");
			return;
		}

		String host = env.getProperty(Constants.SMS_YUNPIAN_SEND_URL);
		String apikey = env.getProperty(Constants.SMS_YUNPIAN_API_KEY);

		Map<String, Object> params = new HashMap<>();
		params.put("apikey", apikey);
		params.put("mobile", mobile);
		params.put("text", content);

		String result = connetionManager.doHttpPost(host, params);

		log.debug("result=" + result);

		if (!StringUtils.isBlank(result)) {
			JSONObject jsonObject = new JSONObject(result);
			if (jsonObject != null && jsonObject.has("code") && jsonObject.getInt("code") == 0) {
				log.info("短信发送成功：sim=" + mobile);
				return;
			}
		}
		log.debug("短信发送失败");
	}

	public void sendOpen(String mobile, int minutes, Long orderId) {
		log.debug("OPEN：sim=" + mobile + ", minutes=" + minutes);
		String open = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND);
		String prefix = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_PREFIX);
		String argType = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_TYPE);
		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_OPEN);
		String argConcat = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_CONCAT);
		String argTime = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_TIME) + (minutes * 60);
		String argOrderId = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_ORDERID) + orderId;
		open = String.format(open, prefix + argType + command + argConcat + argTime + argConcat + argOrderId);
		send(mobile, open);
	}

	public void sendClose(String mobile, Long orderId) {
		log.debug("CLOSE：sim=" + mobile);
		String close = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND);
		String prefix = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_PREFIX);
		String argType = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_TYPE);
		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CLOSE);
		String argConcat = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_CONCAT);
		String argOrderId = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_ORDERID) + orderId;
		close = String.format(close, prefix + argType + command + argConcat + argOrderId);
		send(mobile, close);
	}

	public void sendCheck(String mobile) {
		log.debug("CHECK：sim=" + mobile);
		String check = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND);
		String prefix = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_PREFIX);
		String argType = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_TYPE);
		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CHECK);
		String argConcat = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_CONCAT);
		String argSim = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_SIM);
		check = String.format(check, prefix + argType + command + argConcat + argSim + mobile);
		send(mobile, check);
	}

	@Async
	public void asyncSendCheck(String mobile) {
		sendCheck(mobile);
	}

	public void sendResetURL(String mobile, String url) {
		log.debug("URL：sim=" + mobile + ", url=" + url);
		String resetURL = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND);
		String prefix = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_PREFIX);
		String argType = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_TYPE);
		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_URL);
		String argConcat = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_CONCAT);
		String argUrl = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_ARG_URL) + url;
		resetURL = String.format(resetURL, prefix + argType + command + argConcat + argUrl);
		send(mobile, resetURL);
	}
}
