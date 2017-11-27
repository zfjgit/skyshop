package com.sitv.skyshop.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.dto.ResponseInfo;

@RestController
@RequestMapping("/feign")
public class FeignTestController {

	private static final Logger log = LoggerFactory.getLogger(FeignTestController.class);

	@Autowired
	private ICommonFeignClient feignClient;

	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping("/address/{id}")
	public ResponseInfo<AddressInfo> getAddress(@PathVariable String id) {
		log.debug("id=" + id);
		return feignClient.getAddress(id);
	}

	public ResponseInfo<AddressInfo> fallback(String id) {
		log.debug("fallback method>>>>>>>>>>>>");
		log.debug("id=" + id);
		return ResponseInfo.RUNTIME_ERROR("fallback");
	}
}
