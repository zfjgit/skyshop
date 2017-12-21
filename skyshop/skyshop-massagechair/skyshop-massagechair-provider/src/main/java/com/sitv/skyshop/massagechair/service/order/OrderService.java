/**
 *
 */
package com.sitv.skyshop.massagechair.service.order;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.RandomUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.exception.OrderExpiredException;
import com.sitv.skyshop.common.exception.OrderNotPaidException;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.common.utils.httpclient4.HttpConnectionManager;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.dao.agency.IAgencyDao;
import com.sitv.skyshop.massagechair.dao.device.IInstallationAddressDao;
import com.sitv.skyshop.massagechair.dao.device.IMassageChairDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderDao;
import com.sitv.skyshop.massagechair.dao.order.IOrderIncomePartitionDao;
import com.sitv.skyshop.massagechair.domain.device.InstallationAddress;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.domain.order.Order;
import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;
import com.sitv.skyshop.massagechair.domain.order.Order.PayType;
import com.sitv.skyshop.massagechair.domain.record.UseRecord.UseRecordType;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.massagechair.runner.OrderPayStatusPollRunner;
import com.sitv.skyshop.massagechair.service.userecord.IUseRecordService;
import com.sitv.skyshop.service.CrudService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Slf4j
@Service
public class OrderService extends CrudService<IOrderDao, Order, OrderInfo> implements IOrderService {
	@Autowired
	private IMassageChairDao massageChairDao;

	@Autowired
	private IAgencyDao agencyDao;

	@Autowired
	private IInstallationAddressDao addressDao;

	@Autowired
	private IOrderIncomePartitionDao orderIncomePartitionDao;

	@Autowired
	private IUseRecordService recordService;

	@Autowired
	private Environment env;

	@Autowired
	private HttpConnectionManager httpConnectionManager;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private OrderPayStatusPollRunner orderPayStatusPollRunner;

	public OrderInfo getOne(Long id) {
		return OrderInfo.create(get(id));
	}

	public PageInfo<OrderInfo> search(SearchParamInfo<OrderInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Order> orders = dao.search(q);

		BigDecimal totalMoney = dao.getAgencyOrderTotalMoney(q);

		com.github.pagehelper.PageInfo<Order> pageInfo = new com.github.pagehelper.PageInfo<>(orders, 5);

		List<OrderInfo> orderInfos = OrderInfo.creates(pageInfo.getList());

		return new PageInfo<>(orderInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal(), totalMoney);
	}

	public void updateOne(OrderInfo t) {
		MassageChair chair = massageChairDao.get(t.getChair().getId());

		InstallationAddress address = addressDao.get(t.getInstallationAddress().getId());

		Order order = get(t.getId());
		order.setChair(chair);
		order.setMoney(t.getMoney());
		order.setMinutes(t.getMinutes());
		order.setInstallationAddress(address);
		order.setDescription(t.getDescription());
		order.setAgency(agencyDao.get(t.getAgency().getId()));
		order.setPayStatus(BaseEnum.valueOf(PayStatus.class, t.getPayStatus().getCode()));
		order.setPayType(BaseEnum.valueOf(PayType.class, t.getPayType().getCode()));

		update(order);
	}

	public void createOne(OrderInfo t) {
		if (t.getMoney().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("金额必须大于0");
		}
		if (t.getMinutes() <= 0) {
			throw new IllegalArgumentException("时长必须大于0");
		}
		if (t.getChair() == null || t.getChair().getId() == null) {
			throw new IllegalArgumentException("按摩椅ID不正确");
		}
		MassageChair chair = massageChairDao.get(t.getChair().getId());
		if (chair == null) {
			throw new EntityNotFoundException("未找到按摩椅：ID=" + t.getChair().getId());
		}
		if (chair.getStatus() != ChairStatus.NORMAL) {
			throw new EntityNotFoundException("按摩椅状态不正常");
		}

		String code = Utils.time2String(Calendar.getInstance(), Constants.DATETIME_FORMAT_4) + RandomUtils.nextInt(100, 1000) + chair.getId();

		Order order = new Order(code, t.getMinutes(), t.getMoney(), PayStatus.UNPAID, BaseEnum.valueOf(PayType.class, t.getPayType().getCode()), chair, chair.getAgency(),
		                DeleteStatus.NORMAL, chair.getInstallationAddress());
		create(order);

		t.setId(order.getId());
		t.setCode(code);
	}

	public void deleteOne(Long id) {
		updateDeleteStatus(getOne(id));
	}

	public void updateDeleteStatus(OrderInfo t) {
		Order order = get(t.getId());
		order.setDeleteStatus(BaseEnum.valueOf(DeleteStatus.class, t.getDeleteStatus().getCode()));
		dao.updateDeleteStatus(order);
	}

	public void postPay(OrderInfo t) {
		log.debug("订单支付成功，发送开机指令>>>");
		Order order = get(t.getId());
		if (order == null) {
			throw new EntityNotFoundException("未找到订单：id=" + t.getId());
		}

		if (System.currentTimeMillis() - order.getChairStartTime().getTimeInMillis() >= order.getMinutes() * 60 * 1000) {
			log.debug("charistart=" + Utils.time2String(order.getCreateTime(), Constants.DATETIME_FORMAT_1));
			log.debug("       now=" + Utils.time2String(Calendar.getInstance(), Constants.DATETIME_FORMAT_1));
			log.debug(" ordermins=" + order.getMinutes());
			throw new OrderExpiredException("订单已过期");
		}

		String retries = env.getProperty(Constants.ORDER_PAY_RESULT_CHECK_POLL_RETRIES).trim();
		boolean hasPaid = orderPayStatusPollRunner.run(t.getId(), Integer.valueOf(retries));
		if (!hasPaid) {
			throw new OrderNotPaidException("订单未支付");
		}

		String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);
		UseRecordInfo closeRecord = recordService.getByOrder(order.getId(), UseRecordType.CLOSE.getCode(), operateOk);
		if (closeRecord != null) {
			log.warn("设备已经关机，订单已完成");
			return;
		}

		UseRecordInfo useRecord = recordService.getByOrder(order.getId(), UseRecordType.OPEN.getCode(), operateOk);
		if (useRecord != null) {
			log.warn("已经开机，不能重复开机");
			return;
		}

		orderPartition(order);

		MassageChair chair = order.getChair();
		chair.setStatus(ChairStatus.WORKING);
		massageChairDao.updateStatus(chair);

		UseRecordInfo record = new UseRecordInfo(null, order.getId(), "", EnumInfo.valueOf(UseRecordType.class, UseRecordType.OPEN.getCode()), "微信扫码", "发送开机指令",
		                chair.getGsmModule().getImei(), chair.getGsmModule().getSimCard().getSim(), order.getMoney().toString(), order.getMinutes() + "", chair.getName(),
		                t.getOpenid(), t.getNickName(), chair.getInstallationAddress().getFullAddress(), "", Calendar.getInstance(), null);
		recordService.createOpenRecord(record);

		Calendar chairStartTime = Calendar.getInstance();
		log.debug("chairstart=" + Utils.time2String(chairStartTime, Constants.DATETIME_FORMAT_1));
		dao.updateChairStartTime(chairStartTime, order.getId());
	}

	private void orderPartition(Order order) {
		try {
			log.debug("调用拆账接口>>>");
			String host = env.getProperty(Constants.ORDER_SERVICE_PARTITION_SERVER_URL);
			String params = "?type=SettlementOrder&orders_code=" + order.getCode();

			new Thread(new Runnable() {
				public void run() {
					String result = httpConnectionManager.doHttpGet(host + params);

					log.debug("result=" + result);
					if (!Utils.isNull(result)) {
						JSONObject json = new JSONObject(result);
						if (json.getInt("status") == 1) {
							log.debug("调用拆账接口成功");
							return;
						}
					}
					log.warn("调用拆账接口失败");
				}
			}).start();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public OrderInfo getOrderServiceInfo(Long id) {
		log.debug("获取开机时间信息>>>");
		Order order = get(id);
		if (order == null) {
			throw new EntityNotFoundException("未找到订单：id=" + id);
		}
		OrderInfo t = OrderInfo.create(order);
		t.setContactNumber(order.getInstallationAddress().getContactNumber());

		if (System.currentTimeMillis() - order.getChairStartTime().getTimeInMillis() >= order.getMinutes() * 60 * 1000) {
			log.debug("charistart=" + Utils.time2String(order.getChairStartTime(), Constants.DATETIME_FORMAT_1));
			log.debug("       now=" + Utils.time2String(Calendar.getInstance(), Constants.DATETIME_FORMAT_1));
			log.debug(" ordermins=" + order.getMinutes());
			throw new OrderExpiredException("订单已过期");
		}

		if (!order.getPayStatus().getCode().equals(PayStatus.PAID.getCode())) {
			throw new OrderNotPaidException("订单未支付");
		}

		String operateOk = env.getProperty(Constants.SMS_YUNPIAN_CHAIR_COMMAND_RESULT_OK);
		UseRecordInfo closeRecord = recordService.getByOrder(order.getId(), UseRecordType.CLOSE.getCode(), operateOk);
		if (closeRecord != null) {
			log.warn("设备已经关机，订单已完成");
			t.setLeftSeconds(0);
			return t;
		}

		MassageChair chair = order.getChair();

		OperateResultInfo operateResultInfo = recordService.getOpenOperateResult(chair.getGsmModule().getSimCard().getSim(), t.getId().toString(), 1);
		t.setOperateResultInfo(operateResultInfo);
		log.debug("开机指令结果：=" + operateResultInfo);

		log.debug("chairstart=" + Utils.time2String(order.getChairStartTime(), Constants.DATETIME_FORMAT_1));
		log.debug("       now=" + Utils.time2String(Calendar.getInstance(), Constants.DATETIME_FORMAT_1));

		long pastSeconds = (System.currentTimeMillis() - order.getChairStartTime().getTimeInMillis()) / 1000;
		pastSeconds = Math.max(pastSeconds, 0);
		long leftSeconds = order.getMinutes() * 60 - pastSeconds;
		leftSeconds = Math.min(leftSeconds, order.getMinutes() * 60);
		leftSeconds = Math.max(leftSeconds, 0);

		String adjustSecondsKey = Constants.REDIS_ORDER_SECONDS_ADJUST_KEY + id + chair.getGsmModule().getSimCard().getSim();

		String adjustSeconds = env.getProperty(Constants.OPERATE_RESULT_WAIT_ADJUST_SECONDS).trim();

		int totalSeconds = order.getMinutes() * 60 + Integer.valueOf(adjustSeconds);

		if (operateResultInfo == null || !operateOk.equals(operateResultInfo.getCode())) {
			if (pastSeconds >= totalSeconds) {
				leftSeconds = 0;
			} else {
				leftSeconds = Math.max(totalSeconds - pastSeconds, 0);
			}
			redisTemplate.opsForValue().set(adjustSecondsKey, adjustSeconds);
		} else {
			String adjustedSeconds = redisTemplate.opsForValue().get(adjustSecondsKey);
			if (!Utils.isNull(adjustedSeconds)) {
				leftSeconds = Math.max(totalSeconds - pastSeconds, 0);
			}
		}
		log.debug("leftSeconds=" + leftSeconds);
		t.setLeftSeconds(leftSeconds);
		return t;
	}

	public void updatePayStatus(String orderCode, String payStatus) {
		if (Utils.isNull(orderCode) || Utils.isNull(payStatus)) {
			throw new IllegalArgumentException("参数错误：orderCode=" + orderCode + "/payStatus=" + payStatus);
		}
		Order order = dao.getByCode(orderCode);
		if (order == null) {
			throw new EntityNotFoundException("未找到订单：orderCode=" + orderCode);
		}
		order.setPayStatus(BaseEnum.valueOf(PayStatus.class, payStatus));
		dao.updatePayStatus(order);

		redisTemplate.opsForValue().set(Constants.REDIS_ORDER_PAY_RESULT_KEY_PREFIX + order.getId(), PayStatus.PAID.getCode(), order.getMinutes(), TimeUnit.MINUTES);
	}

	public void partition(Long id) {
		Order order = get(id);
		if (order == null) {
			throw new EntityNotFoundException("未找到订单：id=" + id);
		}
		orderIncomePartitionDao.deleteByOrder(id);
		orderPartition(order);
	}
}
