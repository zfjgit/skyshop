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

import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.common.service.IAddressService;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author zfj20
 * @version 2017年7月27日
 */
@Api(value = "地址信息接口")
@RestController
@RequestMapping("/address")
public class AddressController extends BaseRestController<IAddressService, AddressInfo> {

	@ApiOperation(value = "新增地址", notes = "新增地址", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.CREATED_SUCCESS_CODE, message = "新增成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<AddressInfo> create(@NotNull @Valid @ModelAttribute AddressInfo addressInfo) {
		return super.create(addressInfo);
	}

	@ApiOperation(value = "修改地址", notes = "修改地址", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.UPDATED_SUCCESS_CODE, message = "修改成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<AddressInfo> update(@NotNull @Valid @ModelAttribute AddressInfo addressInfo) {
		return super.update(addressInfo);
	}

}
