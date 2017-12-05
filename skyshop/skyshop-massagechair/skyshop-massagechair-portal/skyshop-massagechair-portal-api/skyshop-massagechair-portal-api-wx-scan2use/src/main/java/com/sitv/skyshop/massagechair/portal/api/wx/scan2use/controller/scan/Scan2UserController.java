/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.scan;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.domain.device.MassageChair;
import com.sitv.skyshop.massagechair.dto.MassageChairResponseInfo;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.service.device.IMassageChairService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年12月1日
 */
@Api("扫码启动按摩椅接口")
@Validated
@RestController
@RequestMapping("/scan2use")
public class Scan2UserController extends BaseController<IMassageChairService, MassageChairInfo> {

	@GetMapping("/{imei}")
	public ResponseInfo<MassageChairInfo> scan(@NotBlank @PathVariable String imei) {
		MassageChairInfo chair = service.getByIMEI(imei);
		if (chair == null) {
			return ResponseInfo.NOT_FOUND_ERROR("没有找到对应的按摩椅");
		}
		if (!MassageChair.ChairStatus.ONLINE.getCode().equals(chair.getStatus())) {
			return MassageChairResponseInfo.UNNORMAL_STATUS("按摩椅状态不正常");
		}
		if (!isChairConnected()) {
			return MassageChairResponseInfo.DISCONNECTED("按摩椅未连接到网络");
		}
		return ResponseInfo.SUCCESS(chair);
	}

	private boolean isChairConnected() {
		return true;
	}
}
