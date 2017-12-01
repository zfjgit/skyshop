/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.device;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.service.device.IMassageChairService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Api("按摩椅接口")
@Validated
@RestController
@RequestMapping("/chair")
public class MassageChairController extends BaseController<IMassageChairService, MassageChairInfo> {

}
