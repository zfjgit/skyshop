/**
 *
 */
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
import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月28日
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderPayStatusPollRunner {

	@Autowired
	private Environment env;

	@Autowired
	private StringRedisTemplate redisTemplate;

	public boolean run(Long id, int retries) {
		log.debug("轮询订单支付结果>>>");

		log.debug("run:thread=" + Thread.currentThread().getId());

		log.debug("id=" + id);
		log.debug("retries=" + retries);

		Long pollPeriod = Long.valueOf(env.getProperty(Constants.ORDER_PAY_RESULT_POLL_PERIOD_MILLISECONDS).trim());

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

			String payStatus = redisTemplate.opsForValue().get(Constants.REDIS_ORDER_PAY_RESULT_KEY_PREFIX + id);
			log.debug("id=" + id + "/payStatus=" + payStatus);
			if (payStatus != null && PayStatus.PAID.getCode().equals(payStatus)) {
				return true;
			}
		}
		return false;
	}

}
