/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.order;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
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

	@GetMapping("/chairincome")
	public ResponseInfo<PageInfo<OrderIncomePartitionInfo>> chairIncome(@NotNull @Valid @ModelAttribute SearchParamInfo<OrderIncomePartitionInfo> searchParamInfo,
	                @NotNull @Valid @ModelAttribute OrderIncomePartitionInfo info) {
		searchParamInfo.setParam(info);
		return ResponseInfo.SUCCESS(service.findChairIncome(searchParamInfo));
	}

	@GetMapping("/chairincomedetail")
	public ResponseInfo<PageInfo<OrderIncomePartitionInfo>> chairIncomeDetail(@NotNull @Valid @ModelAttribute SearchParamInfo<OrderIncomePartitionInfo> searchParamInfo,
	                @NotNull @Valid @ModelAttribute OrderIncomePartitionInfo info) {
		searchParamInfo.setParam(info);
		return ResponseInfo.SUCCESS(service.findChairIncomeDetail(searchParamInfo));
	}

	@GetMapping("/order")
	public ResponseInfo<PageInfo<OrderIncomePartitionInfo>> orderPartitions(@NotNull @Valid @ModelAttribute SearchParamInfo<OrderIncomePartitionInfo> searchParamInfo,
	                @NotNull @Valid @ModelAttribute OrderIncomePartitionInfo info) {
		searchParamInfo.setParam(info);
		return ResponseInfo.SUCCESS(service.findOrderPartitions(searchParamInfo));
	}
}
