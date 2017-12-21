/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.order;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.service.order.IOrderService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("订单管理接口")
@Validated
@RestController("orderManageController")
@RequestMapping("/order")
public class OrderController extends BaseRestController<IOrderService, OrderInfo> {

	@PostMapping("/partition/{id}")
	public ResponseInfo<?> partition(@NotBlank @PathVariable String id) {
		service.partition(Long.valueOf(id));
		return ResponseInfo.SUCCESS(null);
	}
}
