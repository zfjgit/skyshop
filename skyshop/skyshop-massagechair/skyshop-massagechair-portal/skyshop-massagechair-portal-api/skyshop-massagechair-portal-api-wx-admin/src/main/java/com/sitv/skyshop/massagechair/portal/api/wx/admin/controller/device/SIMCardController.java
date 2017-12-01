/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.device;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.device.SIMCardInfo;
import com.sitv.skyshop.massagechair.service.device.ISIMCardService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Api("sim卡接口")
@Validated
@RestController
@RequestMapping("/simcard")
public class SIMCardController extends BaseController<ISIMCardService, SIMCardInfo> {

}
