/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.operate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sitv.skyshop.common.exception.OperateResultReceivException;
import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.massagechair.service.userecord.IUseRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Api("接收设备指令执行结果接口")
@Validated
@Controller
@RequestMapping("/operateresult")
public class ReceivOperateResultController extends BaseController<IUseRecordService, UseRecordInfo> {

	private static final Logger log = LoggerFactory.getLogger(ReceivOperateResultController.class);

	@GetMapping("/receiv")
	@ApiOperation(httpMethod = "GET", value = "系统向设备发送操作指令后，设备通过此接口向系统发送指令执行结果", response = String.class)
	public ResponseEntity<String> receiv(@RequestParam String type, @RequestParam String imei, @RequestParam String code, @RequestParam(required = false) String sim,
	                @RequestParam(required = false) String orderid, @RequestParam(required = false) String starttime) {
		ResponseEntity<String> result = null;
		try {
			log.debug("接收操作指令结果>>>");
			log.debug("type=" + type);
			log.debug("imei=" + imei);
			log.debug("code=" + code);
			log.debug("orderid=" + orderid);
			log.debug("starttime=" + starttime);
			log.debug("sim=" + sim);
			service.saveOperateResult(type, imei, sim, code, orderid, starttime);
			result = new ResponseEntity<>("code=1", HttpStatus.OK);
		} catch (Exception e) {
			throw new OperateResultReceivException("发生错误", e);
		}
		return result;
	}

}
