/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.report;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderIncomePartitionInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.report.AddrOrderPartitionReportVO;
import com.sitv.skyshop.massagechair.service.report.IAddrOrderPartitionReportService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2018年1月8日
 */
@Slf4j
@CrossOrigin
@Api("报表接口")
@Validated
@RestController
@RequestMapping("/report")
public class OrderReportController {

	@Autowired
	private IAddrOrderPartitionReportService service;

	@GetMapping("/addrincomes")
	public ResponseInfo<PageInfo<AddrOrderPartitionReportVO>> getAddrIncomes(@Valid @NotNull @ModelAttribute SearchParamInfo<OrderIncomePartitionInfo> info,
	                @Valid @NotNull @ModelAttribute OrderIncomePartitionInfo param) {
		try {
			log.debug("");
			info.setParam(param);
			return ResponseInfo.SUCCESS(service.findAddrIncomes(info));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseInfo.RUNTIME_ERROR("");
		}
	}

	@GetMapping("/addrorders")
	public ResponseInfo<PageInfo<OrderInfo>> getAddrOrders(@Valid @NotNull @ModelAttribute SearchParamInfo<OrderIncomePartitionInfo> info,
	                @Valid @NotNull @ModelAttribute OrderIncomePartitionInfo param) {
		try {
			log.debug("");
			info.setParam(param);
			return ResponseInfo.SUCCESS(service.findAddrOrders(info));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseInfo.RUNTIME_ERROR("");
		}
	}

	@GetMapping("/addrincomedetails")
	public ResponseInfo<PageInfo<OrderIncomePartitionInfo>> getAddrIncomeDetails(@Valid @NotNull @ModelAttribute SearchParamInfo<OrderIncomePartitionInfo> info,
	                @Valid @NotNull @ModelAttribute OrderIncomePartitionInfo param) {
		try {
			log.debug("");
			info.setParam(param);
			return ResponseInfo.SUCCESS(service.findAddrIncomeDetails(info));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseInfo.RUNTIME_ERROR("");
		}
	}
}
