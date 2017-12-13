/**
 *
 */
package com.sitv.skyshop.massagechair.service.order;

import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.service.IDeleteStatusService;

/**
 * @author zfj20 @ 2017年11月20日
 */
public interface IOrderService extends IBaseService<OrderInfo>, IDeleteStatusService<OrderInfo> {

	String PAYSTATUS_PAID = PayStatus.PAID.getCode();
	String PAYSTATUS_UNPAID = PayStatus.UNPAID.getCode();

	void pay(OrderInfo t);

	OrderInfo getOrderServiceInfo(Long id);

}
