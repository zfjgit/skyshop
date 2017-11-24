/**
 *
 */
package com.sitv.skyshop.common.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.dto.FullAddressInfo;
import com.sitv.skyshop.common.service.IFullAddressService;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Api("组合地址接口")
@RestController
@RequestMapping("/fulladdress")
public class FullAddressController extends BaseController<IFullAddressService, FullAddressInfo> {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseInfo<FullAddressInfo> get(@PathVariable String id) {
		Long fid = NumberUtils.toLong(id, 0);
		if (fid == 0) {
			return ResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}

		return ResponseInfo.SUCCESS(service.getOne(fid));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseInfo<PageInfo<FullAddressInfo>> search(@ModelAttribute SearchParamInfo<FullAddressInfo> paramInfo) {
		return ResponseInfo.SUCCESS(service.search(paramInfo));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<FullAddressInfo> create(@ModelAttribute FullAddressInfo info) {
		if (info == null || info.getProvinceId() == null || info.getCityId() == null || info.getDistrictId() == null || Utils.isNull(info.getDetailAddress())) {
			return ResponseInfo.ARGS_ERROR("参数错误：info=" + new JSONObject(info));
		}

		service.createOne(info);

		return ResponseInfo.CREATED_SUCCESS();
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<FullAddressInfo> update(@ModelAttribute FullAddressInfo info) {
		if (info == null || info.getId() == null || info.getProvinceId() == null || info.getCityId() == null || info.getDistrictId() == null
		                || Utils.isNull(info.getDetailAddress())) {
			return ResponseInfo.ARGS_ERROR("参数错误：info=" + new JSONObject(info));
		}

		service.updateOne(info);

		return ResponseInfo.UPDATED_SUCCESS();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseInfo<FullAddressInfo> delete(@PathVariable String id) {
		Long fid = NumberUtils.toLong(id, 0);
		if (fid == 0) {
			return ResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}
		service.deleteOne(fid);
		return ResponseInfo.DELETED_SUCCESS();
	}

}
