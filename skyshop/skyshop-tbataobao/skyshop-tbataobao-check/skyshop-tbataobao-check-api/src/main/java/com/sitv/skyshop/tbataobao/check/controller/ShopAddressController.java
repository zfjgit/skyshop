/**
 *
 */
package com.sitv.skyshop.tbataobao.check.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.tbataobao.dto.ShopAddressInfo;
import com.sitv.skyshop.tbataobao.service.IShopAddressService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@CrossOrigin
@Api("地址接口")
@Validated
@RestController
@RequestMapping("/shopaddress")
public class ShopAddressController {

	@Autowired
	private IShopAddressService addressService;

	@GetMapping("/childrens/{id}")
	public ResponseInfo<List<ShopAddressInfo>> findChildrens(@PathVariable @Min(0) String id) {
		log.debug("id=" + id);
		List<ShopAddressInfo> childrens = addressService.findChildrens(Long.valueOf(id));
		return ResponseInfo.SUCCESS(childrens);
	}
}
