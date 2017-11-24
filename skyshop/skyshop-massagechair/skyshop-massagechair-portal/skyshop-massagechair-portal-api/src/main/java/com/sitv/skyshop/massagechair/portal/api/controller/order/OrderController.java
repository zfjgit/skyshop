/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.controller.order;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.service.order.IOrderService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("订单接口")
@Validated
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController<IOrderService, OrderInfo> {

}
