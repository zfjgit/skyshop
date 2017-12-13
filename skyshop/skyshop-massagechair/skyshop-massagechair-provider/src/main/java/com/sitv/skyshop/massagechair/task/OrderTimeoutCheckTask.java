package com.sitv.skyshop.massagechair.task;

import java.util.Calendar;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.record.UseRecord.UseRecordType;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.massagechair.service.device.IMassageChairService;
import com.sitv.skyshop.massagechair.service.order.IOrderService;
import com.sitv.skyshop.massagechair.service.userecord.IUseRecordService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderTimeoutCheckTask {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IMassageChairService massageChairService;

	@Autowired
	private IUseRecordService recordService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private Environment env;

	@Scheduled(initialDelayString = "${order.service.check.task.initdelay}", fixedDelayString = "${order.service.check.task.fixeddelay}")
	public void checkTimeout() {
		try {
			log.debug("按摩椅超时检测>>>");
			Set<String> orderIDkeys = redisTemplate.keys(Constants.REDIS_ORDERID_KEY_PREFIX + "*");
			log.debug("orderIDkeys=" + orderIDkeys.size());
			for (String key : orderIDkeys) {
				String orderId = redisTemplate.opsForValue().get(key);
				if (Utils.isNull(orderId)) {
					log.error("获取订单缓存出错：sim=" + key);
					continue;
				}
				OrderInfo orderInfo = orderService.getOne(Long.valueOf(orderId));
				if (orderInfo == null) {
					log.error("获取订单出错：ID=" + orderId);
					continue;
				}

				long beginInMillis = orderInfo.getCreateTime().getTimeInMillis();
				if (System.currentTimeMillis() < beginInMillis) {
					log.error("OS系统时间不正确，请及时同步时间！！！！");
					break;
				}
				log.debug("ordermins=" + orderInfo.getMinutes());
				log.debug("ordertime=" + Utils.time2String(orderInfo.getCreateTime(), Constants.DATETIME_FORMAT_1));
				log.debug("now=" + Utils.time2String(Calendar.getInstance(), Constants.DATETIME_FORMAT_1));
				if (System.currentTimeMillis() - beginInMillis >= orderInfo.getMinutes() * 60 * 1000) {
					log.debug("超时关机>>>");

					redisTemplate.delete(key);

					String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);

					UseRecordInfo useRecordInfo = recordService.getByOrder(orderInfo.getId(), UseRecordType.CLOSE.getCode(), operateOk);
					if (useRecordInfo != null) {
						log.debug("已经关机，不能重复关机");
						continue;
					}

					MassageChairInfo chairInfo = massageChairService.getOne(orderInfo.getChair().getId());

					String sim = chairInfo.getGsmModule().getSimCard().getSim();
					UseRecordInfo recordInfo = new UseRecordInfo(null, orderInfo.getId(), "", EnumInfo.valueOf(UseRecordType.class, UseRecordType.CLOSE.getCode()), "设备超时检查",
					                "发送关机指令", chairInfo.getGsmModule().getImei(), sim, orderInfo.getMoney().toString(), orderInfo.getMinutes() + "", chairInfo.getName(), "", "",
					                chairInfo.getInstallationAddress().getFullAddress(), "", Calendar.getInstance());
					recordService.createCloseRecord(recordInfo);

					log.debug("更新按摩椅状态");
					chairInfo.setStatus(new EnumInfo<>(IMassageChairService.NORMAL, "正常"));
					massageChairService.updateStatus(chairInfo);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
