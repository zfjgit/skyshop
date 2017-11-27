/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.controller.device.malfunction;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.device.malfunction.MalfunctionInfo;
import com.sitv.skyshop.massagechair.service.device.malfunction.IMalfunctionService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月24日
 */
@Api("故障查询接口")
@Validated
@RestController
@RequestMapping("/malfunction")
public class MalfunctionController extends BaseController<IMalfunctionService<MalfunctionInfo>, MalfunctionInfo> {
	public ResponseInfo<MalfunctionInfo> create(MalfunctionInfo info) {
		return ResponseInfo.FORBIDDEN_ERROR("错误的接口调用");
	}

	public ResponseInfo<MalfunctionInfo> update(MalfunctionInfo info) {
		return ResponseInfo.FORBIDDEN_ERROR("错误的接口调用");
	}
}
