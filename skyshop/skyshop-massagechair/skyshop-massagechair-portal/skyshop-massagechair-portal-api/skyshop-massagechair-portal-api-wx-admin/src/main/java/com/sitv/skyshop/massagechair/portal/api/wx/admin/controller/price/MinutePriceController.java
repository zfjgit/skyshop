/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.price;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.massagechair.dto.price.MinutePriceInfo;
import com.sitv.skyshop.massagechair.service.price.IMinutePriceService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("MinutePrice价格接口")
@Validated
@RestController
@RequestMapping("/minuteprice")
public class MinutePriceController extends BaseRestController<IMinutePriceService, MinutePriceInfo> {

}
