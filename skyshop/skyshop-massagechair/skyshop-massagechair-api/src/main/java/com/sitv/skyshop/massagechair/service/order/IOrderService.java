/**
 *
 */
package com.sitv.skyshop.massagechair.service.order;

import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.service.IDeleteStatusService;

/**
 * @author zfj20 @ 2017年11月20日
 */
public interface IOrderService extends IBaseService<OrderInfo>, IDeleteStatusService<OrderInfo> {

}
