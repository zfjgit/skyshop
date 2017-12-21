/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
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
		log.debug("支付成功，发送开机指令>>>");
		log.debug("order=" + orderInfo.getId());
		// 校验参数，防止未支付订单调用
		if (request.getSession().getAttribute(Constants.POSTPAY_ORDERID_SESSION_KEY + orderInfo.getId()) == null) {
			service.postPay(orderInfo);
			request.getSession().setAttribute(Constants.POSTPAY_ORDERID_SESSION_KEY + orderInfo.getId(), System.currentTimeMillis());
		}
		return ResponseInfo.SUCCESS(orderInfo);
	}

	@GetMapping("/serviceinfo/{id}")
	public ResponseInfo<OrderInfo> serviceinfo(@NotBlank @Min(0) @PathVariable String id) {
		log.debug("开机成功，查询服务信息>>>");
		OrderInfo orderInfo = service.getOrderServiceInfo(Long.valueOf(id));
		return ResponseInfo.SUCCESS(orderInfo);
	}

	@PostMapping("/paynotify")
	public void payNotify(HttpServletResponse response) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
			log.debug("处理微信通知.....");

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
					log.debug("订单号=" + out_trade_no);
					log.debug("金额=" + total_fee);
					log.debug("微信流水号=" + transaction_id);

					service.updatePayStatus(out_trade_no, IOrderService.PAYSTATUS_PAID);

					log.debug("返回微信通知处理结果.....");
					response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
