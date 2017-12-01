/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.price;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.price.FixedPriceInfo;
import com.sitv.skyshop.massagechair.service.price.IFixedPriceService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("FixedPrice价格接口")
@Validated
@RestController
@RequestMapping("/fixedprice")
public class FixedPriceController extends BaseController<IFixedPriceService, FixedPriceInfo> {

}
