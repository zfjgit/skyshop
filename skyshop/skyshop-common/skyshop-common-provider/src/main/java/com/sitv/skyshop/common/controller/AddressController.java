/**
 *
 */
package com.sitv.skyshop.common.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.common.dto.CommonResponseInfo;
import com.sitv.skyshop.common.service.IAddressService;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseController;
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
 * @version 2017年7月27日
 */
@Api(value = "地址信息接口")
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController<IAddressService, AddressInfo> {

	private final static Logger log = LoggerFactory.getLogger(AddressController.class);

	@ApiOperation(value = "查询地址", notes = "根据id查询地址", httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "地址id", required = true, paramType = "path", dataType = "Long", defaultValue = "1")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.SUCCESS_CODE, message = "成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseInfo<AddressInfo> get(@PathVariable String id) {
		Long addrId = NumberUtils.toLong(id, 0);
		if (addrId == 0) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}
		AddressInfo info = service.getOne(addrId);

		log.debug("address=" + ToStringBuilder.reflectionToString(info));

		return CommonResponseInfo.SUCCESS(info);
	}

	@ApiOperation(value = "新增地址", notes = "新增地址", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.CREATED_SUCCESS_CODE, message = "新增成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseInfo<AddressInfo> create(@RequestBody AddressInfo addressInfo) {
		if (addressInfo == null) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：参数为空");
		}

		if (Utils.isNull(addressInfo.getName()) || Utils.isNull(addressInfo.getParentId())) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：参数为空");
		}

		service.createOne(addressInfo);

		return CommonResponseInfo.CREATED_SUCCESS();
	}

	@ApiOperation(value = "修改地址", notes = "修改地址", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.UPDATED_SUCCESS_CODE, message = "修改成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseInfo<AddressInfo> update(@RequestBody AddressInfo addressInfo) {
		if (addressInfo == null) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：参数为空");
		}

		if (Utils.isNull(addressInfo.getId()) || Utils.isNull(addressInfo.getName()) || Utils.isNull(addressInfo.getParentId())) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：参数为空");
		}

		service.updateOne(addressInfo);

		return CommonResponseInfo.UPDATED_SUCCESS();
	}

	@ApiOperation(value = "删除地址", notes = "删除地址", httpMethod = "DELETE")
	@ApiImplicitParam(name = "id", value = "地址id", required = true, paramType = "path", dataType = "Long")
	@ApiResponses({ @ApiResponse(code = ResponseInfo.DELETED_SUCCESS_CODE, message = "删除成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseInfo<AddressInfo> delete(@PathVariable String id) {
		Long addrId = NumberUtils.toLong(id, 0);
		if (addrId == 0) {
			return CommonResponseInfo.ARGS_ERROR("参数错误：id=" + id);
		}
		service.deleteOne(addrId);
		return CommonResponseInfo.DELETED_SUCCESS();
	}

	@ApiOperation(value = "查询地址", notes = "查询地址", httpMethod = "GET")
	@ApiImplicitParams({
					// @ApiImplicitParam(name = "param", dataType = "com.sitv.skyshop.common.dto.AddressInfo", paramType = "query", value =
					// "查询参数"),
					// @ApiImplicitParam(name = "param.name", dataType = "String", paramType = "query"),
					// @ApiImplicitParam(name = "param.parentId", dataType = "Long", paramType = "query"),
	})
	@ApiResponses({ @ApiResponse(code = ResponseInfo.SUCCESS_CODE, message = "查询成功"), @ApiResponse(code = ResponseInfo.RUNTIME_ERROR_CODE, message = "运行时错误"),
	                @ApiResponse(code = ResponseInfo.ARGS_ERROR_CODE, message = "参数校验错误"), })
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseInfo<PageInfo<AddressInfo>> search(@ModelAttribute SearchParamInfo<AddressInfo> paramInfo) {
		PageInfo<AddressInfo> pageInfo = service.search(paramInfo);
		return CommonResponseInfo.SUCCESS(pageInfo);
	}
}
