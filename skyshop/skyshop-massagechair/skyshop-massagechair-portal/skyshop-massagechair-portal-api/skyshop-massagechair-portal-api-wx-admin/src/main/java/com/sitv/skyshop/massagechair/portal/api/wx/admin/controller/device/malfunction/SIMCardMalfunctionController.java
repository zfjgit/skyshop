/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.device.malfunction;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.device.malfunction.SIMCardMalfunctionInfo;
import com.sitv.skyshop.massagechair.service.device.malfunction.ISIMCardMalfunctionService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("SIM卡故障信息接口")
@Validated
@RestController
@RequestMapping("/simmalfunction")
public class SIMCardMalfunctionController extends BaseController<ISIMCardMalfunctionService, SIMCardMalfunctionInfo> {

}
