/**
 *
 */
package com.sitv.skyshop.common.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.dto.FullAddressInfo;
import com.sitv.skyshop.common.service.IFullAddressService;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Api("组合地址接口")
@RestController
@RequestMapping("/fulladdress")
public class FullAddressController extends BaseRestController<IFullAddressService, FullAddressInfo> {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<FullAddressInfo> create(@NotNull @Valid @ModelAttribute FullAddressInfo info) {
		return super.create(info);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<FullAddressInfo> update(@NotNull @Valid @ModelAttribute FullAddressInfo info) {
		return super.update(info);
	}

}
