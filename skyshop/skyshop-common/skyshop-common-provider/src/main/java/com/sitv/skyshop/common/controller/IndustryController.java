/**
 *
 */
package com.sitv.skyshop.common.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
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
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;

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
	public ResponseInfo<IndustryInfo> get(@NotBlank @Min(0) @PathVariable String id) {
		return super.get(id);
	}

	public ResponseInfo<IndustryInfo> fallback(String id) {
		log.debug("industry fallback>>>>>>>>>>>");
		log.debug("id=" + id);
		return ResponseInfo.RUNTIME_ERROR("fallback");
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<IndustryInfo> create(@NotNull @Valid @ModelAttribute IndustryInfo info) {
		return super.create(info);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<IndustryInfo> update(@NotNull @Valid @ModelAttribute IndustryInfo info) {
		return super.update(info);
	}

}
