/**
 *
 */
package com.sitv.skyshop.common.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.dto.ConfigurationInfo;
import com.sitv.skyshop.common.service.IConfigurationService;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;

import io.swagger.annotations.Api;
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

	@ApiOperation(value = "新增", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.CREATED_SUCCESS_CODE, message = "新增成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<ConfigurationInfo> create(@NotNull @Valid @ModelAttribute ConfigurationInfo info) {
		return super.create(info);
	}

	@ApiOperation(value = "修改", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.UPDATED_SUCCESS_CODE, message = "修改成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<ConfigurationInfo> update(@NotNull @Valid @ModelAttribute ConfigurationInfo info) {
		return super.update(info);
	}

}
