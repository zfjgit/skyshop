/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.massagechair.dto.order.OrderInfo;
import com.sitv.skyshop.massagechair.service.order.IOrderService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年12月1日
 */
@Api("订单生成接口")
@Validated
@RestController("orderCreateController")
@RequestMapping("/order")
public class OrderController extends BaseRestController<IOrderService, OrderInfo> {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@GetMapping("/paynotify")
	public void payNotify(HttpServletRequest request, HttpServletResponse response) {
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
					// String out_trade_no = resultData.get("out_trade_no");
					// String total_fee = resultData.get("total_fee");
					// String transaction_id = resultData.get("transaction_id");

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

	@PostMapping("/timetick/{openid}/{mins}")
	public void timetick(@Min(1) @PathVariable int mins, @NotBlank @PathVariable String openid, HttpServletRequest request) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			private int seconds = mins * 60;

			public void run() {
				seconds--;
				if (seconds <= 0) {
					t.cancel();
				}

				request.getSession().setAttribute("timetick_" + openid, seconds);
			}
		}, 0, 1000);
	}

}
