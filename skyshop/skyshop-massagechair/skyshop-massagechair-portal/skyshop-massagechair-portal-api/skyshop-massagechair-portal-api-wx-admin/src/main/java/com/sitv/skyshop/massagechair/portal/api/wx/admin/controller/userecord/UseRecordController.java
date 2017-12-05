/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.userecord;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.massagechair.dto.record.UseRecordInfo;
import com.sitv.skyshop.massagechair.service.userecord.IUseRecordService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("设备使用记录接口")
@Validated
@RestController
@RequestMapping("/userecord")
public class UseRecordController extends BaseRestController<IUseRecordService, UseRecordInfo> {

}
