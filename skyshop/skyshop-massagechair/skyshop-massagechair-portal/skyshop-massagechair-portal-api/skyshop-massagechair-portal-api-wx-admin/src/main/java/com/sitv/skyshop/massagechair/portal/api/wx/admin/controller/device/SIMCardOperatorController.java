/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.device;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.device.SIMCardOperatorInfo;
import com.sitv.skyshop.massagechair.service.device.ISIMCardOperatorService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月17日
 */
@Api("sim卡运营商接口")
@Validated
@RestController
@RequestMapping("/simcardoperator")
public class SIMCardOperatorController extends BaseController<ISIMCardOperatorService, SIMCardOperatorInfo> {

}
