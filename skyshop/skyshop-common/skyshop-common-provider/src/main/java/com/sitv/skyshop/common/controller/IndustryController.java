/**
 *
 */
package com.sitv.skyshop.common.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sitv.skyshop.common.dto.IndustryInfo;
import com.sitv.skyshop.common.service.IIndustryService;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Api("行业数据接口")
@RestController
@RequestMapping("/industry")
public class IndustryController extends BaseRestController<IIndustryService, IndustryInfo> {

	private static final Logger log = LoggerFactory.getLogger(IndustryController.class);

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseInfo<IndustryInfo> get(@PathVariable String id) {
		Long inId = NumberUtils.toLong(id, 0);
		if (inId == 0) {
			return ResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}

		return ResponseInfo.SUCCESS(service.getOne(inId));
	}

	public ResponseInfo<IndustryInfo> fallback(String id) {
		log.debug("industry fallback>>>>>>>>>>>");
		log.debug("id=" + id);
		return ResponseInfo.RUNTIME_ERROR("fallback");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseInfo<PageInfo<IndustryInfo>> search(@ModelAttribute SearchParamInfo<IndustryInfo> q) {
		return ResponseInfo.SUCCESS(service.search(q));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<IndustryInfo> create(@ModelAttribute IndustryInfo info) {
		if (info == null || Utils.isNull(info.getCode()) || Utils.isNull(info.getName())) {
			return ResponseInfo.ARGS_ERROR("参数错误：info=" + new JSONObject(info));
		}
		return ResponseInfo.CREATED_SUCCESS();
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<IndustryInfo> update(@ModelAttribute IndustryInfo info) {
		if (info == null || info.getId() == null || Utils.isNull(info.getCode()) || Utils.isNull(info.getName())) {
			return ResponseInfo.ARGS_ERROR("参数错误：info=" + new JSONObject(info));
		}
		return ResponseInfo.UPDATED_SUCCESS();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseInfo<IndustryInfo> delete(@PathVariable String id) {
		Long inId = NumberUtils.toLong(id, 0);
		if (inId == 0) {
			return ResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}
		return ResponseInfo.DELETED_SUCCESS();
	}

}
