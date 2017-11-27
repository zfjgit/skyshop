package com.sitv.skyshop.user.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.dto.ResponseInfo;

@FeignClient("SKYSHOP-COMMON")
public interface ICommonFeignClient {

	@GetMapping("/address/{id}")
	ResponseInfo<AddressInfo> getAddress(@PathVariable("id") String id);
}
