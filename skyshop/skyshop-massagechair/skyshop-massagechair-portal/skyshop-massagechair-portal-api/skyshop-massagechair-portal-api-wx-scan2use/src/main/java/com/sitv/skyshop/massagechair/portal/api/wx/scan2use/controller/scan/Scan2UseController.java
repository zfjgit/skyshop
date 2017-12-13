/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.scan;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.dto.MassageChairResponseInfo;
import com.sitv.skyshop.massagechair.dto.device.MassageChairInfo;
import com.sitv.skyshop.massagechair.dto.record.OperateResultInfo;
import com.sitv.skyshop.massagechair.service.device.IMassageChairService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月1日
 */
@Slf4j
@Api("扫码启动按摩椅接口")
@Validated
@RestController
@RequestMapping("/scan2use")
public class Scan2UseController extends BaseRestController<IMassageChairService, MassageChairInfo> {

	@GetMapping("/{id}")
	public ResponseInfo<MassageChairInfo> get(@NotBlank @Min(0) @PathVariable String id) {
		log.debug("获取按摩椅价格信息>>>");
		MassageChairInfo chair = service.getOne(Long.valueOf(id));
		log.debug("chair=" + chair);
		if (chair == null) {
			return ResponseInfo.NOT_FOUND_ERROR("没有找到对应的按摩椅");
		}
		service.asyncCheckServiceStatus(chair.getId());
		return ResponseInfo.SUCCESS(chair);
	}

	@GetMapping("/check/{id}")
	public ResponseInfo<OperateResultInfo> check(@NotBlank @Min(0) @PathVariable String id) {
		log.debug("检测按摩椅状态>>>");
		MassageChairInfo chair = service.getOne(Long.valueOf(id));
		if (chair == null) {
			return ResponseInfo.NOT_FOUND_ERROR("没有找到对应的按摩椅");
		}
		if (!ChairStatus.NORMAL.getCode().equals(chair.getStatus().getCode())) {
			return MassageChairResponseInfo.UNNORMAL_STATUS("按摩椅状态不正常");
		}
		OperateResultInfo info = service.checkServiceStatus(chair.getId());
		log.debug("operateResultInfo=" + info);
		return ResponseInfo.SUCCESS(info);
	}
}
