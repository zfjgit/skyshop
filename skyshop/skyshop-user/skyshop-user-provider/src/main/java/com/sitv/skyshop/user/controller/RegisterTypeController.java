/**
 *
 */
package com.sitv.skyshop.user.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.user.dto.RegisterTypeInfo;
import com.sitv.skyshop.user.service.IRegisterTypeService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Api("用户注册类型接口")
@RestController
@RequestMapping("/registertype")
public class RegisterTypeController extends BaseController<IRegisterTypeService, RegisterTypeInfo> {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseInfo<RegisterTypeInfo> get(@PathVariable String id) {
		Long rid = NumberUtils.toLong(id, 0);
		if (rid == 0) {
			return ResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}
		return ResponseInfo.SUCCESS(service.getOne(rid));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseInfo<PageInfo<RegisterTypeInfo>> search(@ModelAttribute SearchParamInfo<RegisterTypeInfo> paramInfo) {
		return ResponseInfo.SUCCESS(service.search(paramInfo));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<RegisterTypeInfo> create(@ModelAttribute RegisterTypeInfo info) {
		if (info == null || Utils.isNull(info.getCode()) || Utils.isNull(info.getName())) {
			return ResponseInfo.ARGS_ERROR("参数错误：info=" + new JSONObject(info));
		}
		service.createOne(info);
		return ResponseInfo.CREATED_SUCCESS();
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<RegisterTypeInfo> update(@ModelAttribute RegisterTypeInfo info) {
		if (info == null || info.getId() == null || Utils.isNull(info.getCode()) || Utils.isNull(info.getName())) {
			return ResponseInfo.ARGS_ERROR("参数错误：info=" + new JSONObject(info));
		}
		service.updateOne(info);
		return ResponseInfo.UPDATED_SUCCESS();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseInfo<RegisterTypeInfo> delete(@PathVariable String id) {
		Long rid = NumberUtils.toLong(id, 0);
		if (rid == 0) {
			return ResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}
		service.deleteOne(rid);
		return ResponseInfo.DELETED_SUCCESS();
	}

}
