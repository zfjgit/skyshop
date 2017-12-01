/**
 *
 */
package com.sitv.skyshop.common.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.dto.CommonResponseInfo;
import com.sitv.skyshop.common.dto.ConfigurationInfo;
import com.sitv.skyshop.common.service.IConfigurationService;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.SearchParamInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
@Api(value = "基础数据接口")
@RestController
@RequestMapping("/configuration")
public class ConfigurationController extends BaseRestController<IConfigurationService, ConfigurationInfo> {

	private final static Logger log = LoggerFactory.getLogger(ConfigurationController.class);

	@ApiOperation(value = "查询一条", httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "Long", defaultValue = "1")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.SUCCESS_CODE, message = "成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseInfo<ConfigurationInfo> get(String id) {
		Long cid = NumberUtils.toLong(id, 0);
		if (cid == 0) {
			log.error("参数错误：id=" + id);
			return ResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}

		ConfigurationInfo configurationInfo = service.getOne(cid);

		log.debug("configurationInfo=" + new JSONObject(configurationInfo));

		return ResponseInfo.SUCCESS(configurationInfo);
	}

	@ApiOperation(value = "查询", httpMethod = "GET")
	@ApiImplicitParams({
					// @ApiImplicitParam(name = "param", dataType = "com.sitv.skyshop.common.dto.AddressInfo", paramType = "query", value =
					// "查询参数"),
					// @ApiImplicitParam(name = "param.name", dataType = "String", paramType = "query"),
					// @ApiImplicitParam(name = "param.parentId", dataType = "Long", paramType = "query"),
	})
	@ApiResponses({ @ApiResponse(code = ResponseInfo.SUCCESS_CODE, message = "查询成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseInfo<PageInfo<ConfigurationInfo>> search(@ModelAttribute SearchParamInfo<ConfigurationInfo> paramInfo) {
		log.debug("params = " + new JSONObject(paramInfo));
		PageInfo<ConfigurationInfo> pageInfo = service.search(paramInfo);
		return ResponseInfo.SUCCESS(pageInfo);
	}

	@ApiOperation(value = "新增", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.CREATED_SUCCESS_CODE, message = "新增成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<ConfigurationInfo> create(@ModelAttribute ConfigurationInfo info) {
		if (info == null || Utils.isNull(info.getId()) || Utils.isNull(info.getName()) || Utils.isNull(info.getParentId()) || Utils.isNull(info.getClassification())) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：参数为空");
		}

		service.createOne(info);
		return ResponseInfo.CREATED_SUCCESS();
	}

	@ApiOperation(value = "修改", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.UPDATED_SUCCESS_CODE, message = "修改成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<ConfigurationInfo> update(ConfigurationInfo info) {
		if (info == null || Utils.isNull(info.getId()) || Utils.isNull(info.getName()) || Utils.isNull(info.getParentId()) || Utils.isNull(info.getClassification())) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：参数为空");
		}

		service.updateOne(info);
		return ResponseInfo.UPDATED_SUCCESS();
	}

	@ApiOperation(value = "删除", httpMethod = "DELETE")
	@ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "Long")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.DELETED_SUCCESS_CODE, message = "删除成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseInfo<ConfigurationInfo> delete(String id) {
		Long cid = NumberUtils.toLong(id, 0);
		if (cid == 0) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}

		service.deleteOne(cid);
		return ResponseInfo.DELETED_SUCCESS();

	}
}
