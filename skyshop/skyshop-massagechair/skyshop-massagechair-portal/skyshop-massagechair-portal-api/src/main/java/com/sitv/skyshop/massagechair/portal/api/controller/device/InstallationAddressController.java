/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.controller.device;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.device.InstallationAddressInfo;
import com.sitv.skyshop.massagechair.service.device.IInstallationAddressService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Api("安装地址接口")
@Validated
@RestController
@RequestMapping("/installaddr")
public class InstallationAddressController extends BaseController<IInstallationAddressService, InstallationAddressInfo> {

	public ResponseInfo<InstallationAddressInfo> get(String id) {
		return super.get(id);
	}
}
