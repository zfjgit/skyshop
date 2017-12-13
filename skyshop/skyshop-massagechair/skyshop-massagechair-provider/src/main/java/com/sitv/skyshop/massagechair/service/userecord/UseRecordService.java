/**
 *
 */
package com.sitv.skyshop.massagechair.service.userecord;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.exception.EntityNotFoundException;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.common.utils.sender.SMSSender;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderDao;
import com.sitv.skyshop.massagechair.dao.userecord.IUseRecordDao;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.record.UseRecord;
import com.sitv.skyshop.massagechair.domain.record.UseRecord.UseRecordType;
import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.service.CrudService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Slf4j
@Service
public class UseRecordService extends CrudService<IUseRecordDao, UseRecord, UseRecordInfo> implements IUseRecordService {

	@Autowired
	private Environment env;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private SMSSender smsSender;

	@Autowired
	private IMassageChairDao chairDao;
	@Autowired
	private IOrderDao orderDao;

	public UseRecordInfo getOne(Long id) {
		return UseRecordInfo.create(get(id));
	}

	public PageInfo<UseRecordInfo> search(SearchParamInfo<UseRecordInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<UseRecord> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<UseRecord> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<UseRecordInfo> infos = UseRecordInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(UseRecordInfo t) {
		throw new UnsupportedOperationException("不能修改");
	}

	public void deleteOne(Long id) {
		throw new UnsupportedOperationException("不能删除");
	}

	public void createOne(UseRecordInfo t) {
		UseRecord r = new UseRecord(null, t.getOrderId(), t.getResultCode(), t.getFrom(), t.getResponse(), BaseEnum.valueOf(UseRecordType.class, t.getType().getCode()),
		                t.getImei(), t.getSim(), t.getPrice(), t.getMinutes(), t.getChair(), t.getOpenid(), t.getNickName(), t.getAddr(), t.getUrl());

		create(r);
	}

	public void saveOperateResult(String command, String imei, String code, String orderId) {
		log.debug("保存指令操作结果>>>");
		if (Utils.isNull(command)) {
			throw new IllegalArgumentException("参数错误：type=" + command);
		}
		if (Utils.isNull(imei)) {
			throw new IllegalArgumentException("参数错误：imei=" + imei);
		}

		String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);
		String operateFail = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_FAIL);
		if (Utils.isNull(code) || !Arrays.asList(operateOk, operateFail).contains(code)) {
			throw new IllegalArgumentException("参数错误：code=" + code);
		}

		String open = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_OPEN);
		String close = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CLOSE);
		String check = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CHECK);
		String url = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_URL);

		if (!Arrays.asList(open, close, check, url).contains(command)) {
			throw new IllegalArgumentException("参数错误：type=" + command);
		}
		if (Arrays.asList(open, close).contains(command) && Utils.isNull(orderId)) {
			throw new IllegalArgumentException("参数错误：orderId=" + orderId);
		}

		MassageChair chair = chairDao.getByIMEI(imei);
		if (chair == null) {
			throw new EntityNotFoundException("没有找到按摩椅：IMEI=" + imei);
		}

		String sim = chair.getGsmModule().getSimCard().getSim();

		if (operateOk.equals(code)) {
			String type = "";
			String commandKey = command + sim;
			if (command.equals(open)) {
				type = UseRecordType.OPEN.getCode();
				commandKey = command + orderId + sim;
			} else if (command.equals(close)) {
				type = UseRecordType.CLOSE.getCode();
				String key = Constants.REDIS_ORDERID_KEY_PREFIX + orderId + sim;
				log.debug("已成功关机，删除缓存");
				log.debug("key=" + key);

				Order orderInfo = orderDao.get(Long.valueOf(orderId));

				if (orderInfo == null) {
					log.error("没有找到订单：orderId=" + orderId);
					return;
				}

				UseRecordInfo useRecordInfo = getByOrder(orderInfo.getId(), UseRecordType.CLOSE.getCode(), operateOk);
				if (useRecordInfo != null) {
					log.debug("已经关机，不能重复关机");
					return;
				}

				log.debug("更新按摩椅状态");

				MassageChair chairInfo = chairDao.get(orderInfo.getChair().getId());
				chairInfo.setStatus(ChairStatus.NORMAL);
				chairDao.updateStatus(chairInfo);

				commandKey = command + orderId + sim;

				redisTemplate.delete(key);
			} else if (command.equals(check)) {
				type = UseRecordType.CHECK.getCode();
			} else if (command.equals(url)) {
				type = UseRecordType.URL.getCode();
			}

			UseRecord t = dao.getLast(sim, type, orderId);
			if (t == null) {
				log.error("没有找到对应的指令发出记录：type=" + command + "/sim=" + sim + "/orderId=" + orderId);
				return;
			}

			log.debug("保存：type=" + command + "/sim=" + sim + "/code=" + code + "/orderId=" + orderId);
			int experid = Integer.parseInt(env.getProperty(Constants.OPERATE_RESULT_CACHE_EXPIRED_MINUTES).trim());
			redisTemplate.opsForValue().set(commandKey, code, experid, TimeUnit.MINUTES);

			UseRecord r = new UseRecord(null, Utils.isNull(orderId) ? null : Long.valueOf(orderId), code, "接收设备指令操作结果", "设备指令执行成功", BaseEnum.valueOf(UseRecordType.class, type),
			                imei, sim, t.getPrice(), t.getMinutes(), chair.getName(), t.getOpenid(), t.getNickName(), chair.getInstallationAddress().getFullAddress(), t.getUrl());
			dao.insert(r);
		}
	}

	public UseRecordInfo getByOrder(Long orderId, String type, String resultCode) {
		return UseRecordInfo.create(dao.getByOrder(orderId, type, resultCode));
	}

	public void createOpenRecord(UseRecordInfo recordInfo) {
		log.debug("发送开机指令>>>");

		Order order = orderDao.get(recordInfo.getOrderId());

		String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);

		UseRecord useRecord = dao.getByOrder(order.getId(), UseRecordType.OPEN.getCode(), operateOk);
		if (useRecord != null) {
			log.warn("已经开机，不能重复操作");
			return;
		}

		createOne(recordInfo);

		MassageChair chair = chairDao.get(order.getChair().getId());
		String sim = chair.getGsmModule().getSimCard().getSim();

		String orderIdKey = Constants.REDIS_ORDERID_KEY_PREFIX + order.getId().toString() + sim;
		redisTemplate.opsForValue().set(orderIdKey, order.getId().toString());

		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_OPEN);
		String commandKey = command + order.getId() + sim;
		redisTemplate.opsForValue().set(commandKey, "0");

		smsSender.sendOpen(sim, order.getMinutes(), order.getId());
	}

	public void createCloseRecord(UseRecordInfo recordInfo) {
		log.debug("发送关机指令>>>");
		String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);

		UseRecord useRecord = dao.getByOrder(recordInfo.getOrderId(), UseRecordType.CLOSE.getCode(), operateOk);
		if (useRecord != null) {
			log.warn("已经关机，不能重复操作");
			return;
		}

		createOne(recordInfo);

		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CLOSE);
		String commandKey = command + recordInfo.getOrderId() + recordInfo.getSim();

		redisTemplate.opsForValue().set(commandKey, "0");

		smsSender.sendClose(recordInfo.getSim(), recordInfo.getOrderId());
	}

	public void createCheckRecord(UseRecordInfo recordInfo) {
		log.debug("发送检测指令>>>");
		createOne(recordInfo);

		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CHECK);
		String commandKey = command + recordInfo.getSim();
		redisTemplate.opsForValue().set(commandKey, "0");

		smsSender.sendCheck(recordInfo.getSim());
	}

	public void createAsyncCheckRecord(UseRecordInfo recordInfo) {
		log.debug("异步发送检测指令>>>");
		createOne(recordInfo);

		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CHECK);
		String commandKey = command + recordInfo.getSim();
		redisTemplate.opsForValue().set(commandKey, "0");

		smsSender.asyncSendCheck(recordInfo.getSim());
	}

	public void createResetUrlRecord(UseRecordInfo recordInfo) {
		log.debug("发送重置接口URL指令>>>");
		createOne(recordInfo);

		String command = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_URL);
		String commandKey = command + recordInfo.getSim();
		redisTemplate.opsForValue().set(commandKey, "0");

		smsSender.sendResetURL(recordInfo.getSim(), recordInfo.getUrl());
	}

	public OperateResultInfo getOperateResult(String key, int retries) {
		log.debug("获取指令操作结果>>>");
		log.debug("key=" + key);
		if (Utils.isNull(key)) {
			throw new IllegalArgumentException("参数错误：key=" + key);
		}

		String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);
		String operateFail = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_FAIL);
		Long checkPeriod = Long.valueOf(env.getProperty(Constants.OPERATE_RESULT_CHECK_PERIOD_MILLISECONDS).trim());

		String code = redisTemplate.opsForValue().get(key);
		log.debug("code=" + code);
		if (code == null) {
			return null;
		}
		if (operateOk.equals(code)) {
			return new OperateResultInfo(operateOk, "成功");
		} else {
			if (retries > 0) {
				for (int i = 0; i < retries; i++) {
					if (i > 9) {
						break;
					}
					try {
						Thread.sleep(checkPeriod);
					} catch (InterruptedException e) {
						log.error(e.getMessage(), e);
					}
					code = redisTemplate.opsForValue().get(key);
					if (code == null) {
						return null;
					}
					if (operateOk.equals(code)) {
						return new OperateResultInfo(operateOk, "成功");
					}
				}
			}
			return new OperateResultInfo(operateFail, "失败");
		}
	}

	public OperateResultInfo getOpenOperateResult(String sim, String orderId, int retries) {
		String open = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_OPEN);
		return getOperateResult(open + orderId + sim, retries);
	}

	public OperateResultInfo getCloseOperateResult(String sim, String orderId, int retries) {
		String close = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CLOSE);
		return getOperateResult(close + orderId + sim, retries);
	}

	public OperateResultInfo getCheckOperateResult(String sim, int retries) {
		String check = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_CHECK);
		return getOperateResult(check + sim, retries);
	}

	public OperateResultInfo getResetUrlOperateResult(String sim, int retries) {
		String url = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_URL);
		return getOperateResult(url + sim, retries);
	}

	public void createRecord(UseRecordInfo recordInfo) {
		log.debug("发送指令>>>");
		log.debug("recordInfo=" + recordInfo);
		if (recordInfo.getType().getCode().equals(UseRecordType.CHECK.getCode())) {
			createCheckRecord(recordInfo);
		} else if (recordInfo.getType().getCode().equals(UseRecordType.CLOSE.getCode())) {
			createCloseRecord(recordInfo);
		} else if (recordInfo.getType().getCode().equals(UseRecordType.OPEN.getCode())) {
			createOpenRecord(recordInfo);
		} else if (recordInfo.getType().getCode().equals(UseRecordType.URL.getCode())) {
			createResetUrlRecord(recordInfo);
		} else {
			throw new IllegalArgumentException("错误的指令类型：type=" + recordInfo.getType().getCode());
		}
	}

}
