/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.service.order.IOrderService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月1日
 */
@Slf4j
@Api("订单生成接口")
@Validated
@RestController
@RequestMapping("/order")
public class OrderController extends BaseRestController<IOrderService, OrderInfo> {

	@PostMapping("/postpay/")
	public ResponseInfo<OrderInfo> postpay(@Valid @NotNull @ModelAttribute OrderInfo orderInfo) {
		// 校验参数，防止未支付订单调用
		orderInfo.setPayStatus(new EnumInfo<>(IOrderService.PAYSTATUS_PAID, "已支付"));
		service.pay(orderInfo);
		orderInfo = service.getOrderServiceInfo(orderInfo.getId());
		return ResponseInfo.SUCCESS(orderInfo);
	}

	@PostMapping("/restart/{chairId}")
	public ResponseInfo<OrderInfo> restart(@Valid @NotNull Long chairId) {
		return ResponseInfo.SUCCESS(null);
	}

	@PostMapping("/paynotify")
	public void payNotify(HttpServletResponse response) {
		BufferedReader reader = null;
		try {
			log.debug("处理微信通知.....");

			reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

			StringBuilder result = new StringBuilder();

			String line = "";
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			log.debug("result=" + result.toString());

			Map<String, String> resultData = Utils.parseXml(result.toString());

			if ("SUCCESS".equals(resultData.get("return_code"))) {
				if ("SUCCESS".equals(resultData.get("result_code"))) {
					String out_trade_no = resultData.get("out_trade_no");
					String total_fee = resultData.get("total_fee");
					String transaction_id = resultData.get("transaction_id");
					log.debug("out_trade_no=" + out_trade_no);
					log.debug("total_fee=" + total_fee);
					log.debug("transaction_id=" + transaction_id);

					log.debug("返回微信通知处理结果.....");
					response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}
