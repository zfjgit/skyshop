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

import com.sitv.skyshop.common.dto.SysCategoryInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.tbataobao.service.ISysCategoryService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@CrossOrigin
@Api("系统分类接口")
@Validated
@RestController
@RequestMapping("/syscategory")
public class SysCategoryController {

	@Autowired
	private ISysCategoryService sysCategoryService;

	@GetMapping("/childrens/{id}")
	public ResponseInfo<List<SysCategoryInfo>> findChildrens(@PathVariable @Min(0) String id) {
		log.debug("id=" + id);
		List<SysCategoryInfo> childrens = sysCategoryService.findChildrens(Long.valueOf(id));
		return ResponseInfo.SUCCESS(childrens);
	}
}
