/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.device;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.service.device.IMassageChairService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Api("按摩椅接口")
@Validated
@RestController
@RequestMapping("/chair")
public class MassageChairController extends BaseRestController<IMassageChairService, MassageChairInfo> {

	@GetMapping("/check/{id}")
	public ResponseInfo<?> checkServiceStatus(@NotBlank @Min(0) @PathVariable String id) {
		return ResponseInfo.SUCCESS(service.checkServiceStatus(Long.valueOf(id)));
	}

	@PostMapping("/restart/{id}")
	public ResponseInfo<?> restart(@NotBlank @PathVariable String id) {
		MassageChairInfo info = service.getOne(Long.valueOf(id));
		info.setStatus(new EnumInfo<>(IMassageChairService.NORMAL, "正常未使用"));
		service.updateStatus(info);
		return ResponseInfo.SUCCESS(info);
	}

	@PostMapping("/autocreate/{sim}")
	public ResponseInfo<?> asyncAutoCreate(@NotBlank @PathVariable String sim) {
		String[] sims = sim.split(",");
		for (String s : sims) {
			if (s != null && s.trim().length() == 11 && NumberUtils.isDigits(s.trim())) {
				service.asyncAutoCreateChair(s);
			}
		}
		return ResponseInfo.SUCCESS(null);
	}

	@PostMapping("/createqrcode/{id}")
	public ResponseInfo<?> createQRCode(@NotBlank @PathVariable String id) {
		return ResponseInfo.SUCCESS(service.createQRCode(Long.valueOf(id)));
	}
}
