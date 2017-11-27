/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.controller.price;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.price.PriceInfo;
import com.sitv.skyshop.massagechair.service.price.IPriceService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("价格查询接口")
@Validated
@RestController
@RequestMapping("/price")
public class PriceController extends BaseController<IPriceService<PriceInfo>, PriceInfo> {

	public ResponseInfo<PriceInfo> create(PriceInfo info) {
		return ResponseInfo.FORBIDDEN_ERROR("错误的接口调用");
	}

	public ResponseInfo<PriceInfo> update(PriceInfo info) {
		return ResponseInfo.FORBIDDEN_ERROR("错误的接口调用");
	}
}
