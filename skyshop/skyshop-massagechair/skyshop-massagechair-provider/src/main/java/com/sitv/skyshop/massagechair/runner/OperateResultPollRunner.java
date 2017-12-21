package com.sitv.skyshop.massagechair.runner;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OperateResultPollRunner {

	@Autowired
	private Environment env;

	@Autowired
	private StringRedisTemplate redisTemplate;

	public OperateResultInfo run(String key, int retries) {
		log.debug("轮询指令执行结果>>>");

		String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);
		String operateFail = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_FAIL);
		Long pollPeriod = Long.valueOf(env.getProperty(Constants.OPERATE_RESULT_POLL_PERIOD_MILLISECONDS).trim());

		log.debug("run:thread=" + Thread.currentThread().getId());

		log.debug("key=" + key);
		log.debug("retries=" + retries);
		for (int i = 0; i < retries; i++) {
			if (i > 9) {
				break;
			}

			try {
				Thread t = new Thread(new Runnable() {
					public void run() {
						log.debug("for:thread=" + Thread.currentThread().getId());
						try {
							Thread.sleep(pollPeriod);
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
						}
					}
				});
				t.start();
				t.join();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}

			log.debug((i + 1) + " ---- " + Utils.time2String(Calendar.getInstance(), Constants.DATETIME_FORMAT_1));

			String resultCode = redisTemplate.opsForValue().get(key);
			log.debug("key=" + key + "/resultCode=" + resultCode);

			if (resultCode != null && operateOk.equals(resultCode)) {
				return new OperateResultInfo(operateOk, "");
			}
		}
		return new OperateResultInfo(operateFail, "");
	}

}
