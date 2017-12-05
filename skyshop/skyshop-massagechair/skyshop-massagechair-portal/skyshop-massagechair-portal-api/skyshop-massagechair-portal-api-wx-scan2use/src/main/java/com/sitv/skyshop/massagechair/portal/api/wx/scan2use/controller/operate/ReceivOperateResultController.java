/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.operate;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.service.device.IMassageChairService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Api("接收设备指令执行结果接口")
@Validated
@RestController
@RequestMapping("/operateresult")
public class ReceivOperateResultController extends BaseController<IMassageChairService, MassageChairInfo> {

	private static final Logger log = LoggerFactory.getLogger(ReceivOperateResultController.class);

	@ApiOperation(httpMethod = "POST", value = "系统向设备发送操作指令后，设备通过此接口向系统发送指令执行结果", response = String.class)
	@PostMapping("/receiv")
	public String receiv(HttpServletRequest request) {
		String type = request.getParameter("type");
		String sim = request.getParameter("sim");
		String code = request.getParameter("code");

		log.info("type=" + type);
		log.info("sim=" + sim);
		log.info("code=" + code);

		return "code=1";
	}

}
