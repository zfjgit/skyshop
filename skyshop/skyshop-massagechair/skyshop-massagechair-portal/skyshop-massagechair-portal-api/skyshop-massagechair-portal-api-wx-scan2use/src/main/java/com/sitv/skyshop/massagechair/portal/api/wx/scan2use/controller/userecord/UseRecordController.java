/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.userecord;

import java.util.Calendar;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.massagechair.service.userecord.IUseRecordService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年12月20日
 */
@Api("发送设备指令接口")
@Validated
@Controller
@RequestMapping("/userecord")
public class UseRecordController extends BaseRestController<IUseRecordService, UseRecordInfo> {

	@PostMapping("/")
	public ResponseInfo<UseRecordInfo> create(@NotNull @Valid @ModelAttribute UseRecordInfo info) {
		UseRecordInfo recordInfo = new UseRecordInfo(null, info.getOrderId(), "", info.getType(), "测试", "手动发送指令", info.getImei(), info.getSim(), "", "", "", "", "", "",
		                info.getUrl(), Calendar.getInstance(), null);
		service.createRecord(recordInfo);
		return ResponseInfo.SUCCESS(recordInfo);
	}
}
