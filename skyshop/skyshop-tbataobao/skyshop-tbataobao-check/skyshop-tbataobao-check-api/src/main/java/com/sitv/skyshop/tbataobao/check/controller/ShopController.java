/**
 *
 */
package com.sitv.skyshop.tbataobao.check.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.tbataobao.domain.ICheckStatus.CheckStatus;
import com.sitv.skyshop.tbataobao.dto.ShopInfo;
import com.sitv.skyshop.tbataobao.service.IShopService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2018年3月24日
 */
@CrossOrigin
@Api("店铺信息接口")
@Validated
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private IShopService shopService;

	@GetMapping("/{id}")
	public ResponseInfo<Map<String, Object>> get(@PathVariable @Min(0) String id) {
		Map<String, Object> shop = shopService.get(Long.valueOf(id));
		return ResponseInfo.SUCCESS(shop);
	}

	@GetMapping("/")
	public ResponseInfo<PageInfo<Map<String, Object>>> find(@NotNull @Valid @ModelAttribute SearchParamInfo<ShopInfo> searchParamInfo,
	                @NotNull @Valid @ModelAttribute ShopInfo params) {
		searchParamInfo.setParam(params);
		PageInfo<Map<String, Object>> shops = shopService.find(searchParamInfo);
		return ResponseInfo.SUCCESS(shops);
	}

	@PutMapping("/")
	public ResponseInfo<ShopInfo> updateCheckStatus(@NotNull @Valid @ModelAttribute("shopInfo") ShopInfo shopInfo) {
		if (shopInfo.getCheckStatus().getCode().equals(CheckStatus.FAILED.getCode()) && shopInfo.getRemarkInfo() == null) {
			return ResponseInfo.ARGS_ERROR("必须输入审核不通过的原因");
		}

		shopService.updateCheckStatus(shopInfo);
		return ResponseInfo.UPDATED_SUCCESS(shopInfo);
	}

	@PutMapping("/batchcheck")
	public ResponseInfo<String> batchCheck(@NotBlank @RequestParam String code, @NotEmpty @RequestParam("ids[]") List<Long> ids, @RequestParam(required = false) String remark) {
		if (code.equals(CheckStatus.FAILED.getCode()) && Utils.isNull(remark)) {
			return ResponseInfo.ARGS_ERROR("必须输入审核不通过原因");
		}
		shopService.batchCheck(code, ids, remark);
		return ResponseInfo.UPDATED_SUCCESS("");
	}
}
