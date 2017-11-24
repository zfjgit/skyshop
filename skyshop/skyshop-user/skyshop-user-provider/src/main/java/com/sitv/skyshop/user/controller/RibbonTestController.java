package com.sitv.skyshop.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.dto.ResponseInfo;

@RestController
@RequestMapping("/ribbon")
public class RibbonTestController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/address/{id}")
	public ResponseInfo<AddressInfo> getAddress(@PathVariable String id) {
		@SuppressWarnings("unchecked")
		ResponseInfo<AddressInfo> r = restTemplate.getForObject("http://SKYSHOP-COMMON/address/" + id, ResponseInfo.class);
		return r;
	}
}
