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
import com.sitv.skyshop.tbataobao.dto.ProductInfo;
import com.sitv.skyshop.tbataobao.service.IProductService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2018年3月23日
 */
@Slf4j
@CrossOrigin
@Api("商品信息接口")
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping("/{id}")
	public ResponseInfo<Map<String, Object>> get(@PathVariable @Min(0) String id) {
		Map<String, Object> product = productService.get(Long.valueOf(id));
		return ResponseInfo.SUCCESS(product);
	}

	@PutMapping("/batchcheck")
	public ResponseInfo<String> batchCheck(@NotBlank @RequestParam String code, @NotEmpty @RequestParam("ids[]") List<Long> ids, @RequestParam(required = false) String remark) {
		if (code.equals(CheckStatus.FAILED.getCode()) && Utils.isNull(remark)) {
			return ResponseInfo.ARGS_ERROR("必须输入审核不通过原因");
		}
		productService.batchCheck(code, ids, remark);
		return ResponseInfo.UPDATED_SUCCESS("");
	}

	@GetMapping("/")
	public ResponseInfo<PageInfo<Map<String, Object>>> find(@NotNull @Valid @ModelAttribute SearchParamInfo<ProductInfo> searchParamInfo,
	                @NotNull @Valid @ModelAttribute ProductInfo params) {
		searchParamInfo.setParam(params);
		log.debug("searchParamInfo=" + searchParamInfo);
		PageInfo<Map<String, Object>> products = productService.find(searchParamInfo);
		return ResponseInfo.SUCCESS(products);
	}

	@PutMapping("/")
	public ResponseInfo<ProductInfo> updateCheckStatus(@NotNull @Valid @ModelAttribute("productInfo") ProductInfo productInfo) {
		if (productInfo.getCheckStatus().getCode().equals(CheckStatus.FAILED.getCode()) && productInfo.getRemarkInfo() == null) {
			return ResponseInfo.ARGS_ERROR("必须输入审核不通过的原因");
		}
		productService.updateCheckStatus(productInfo);
		return ResponseInfo.UPDATED_SUCCESS(productInfo);
	}
}
