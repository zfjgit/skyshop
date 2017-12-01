/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.order;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;
import com.sitv.skyshop.massagechair.service.order.IOrderIncomePartitionService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("订单分成接口")
@Validated
@RestController
@RequestMapping("/orderpartition")
public class OrderIncomePartitionController extends BaseRestController<IOrderIncomePartitionService, OrderIncomePartitionInfo> {

}
