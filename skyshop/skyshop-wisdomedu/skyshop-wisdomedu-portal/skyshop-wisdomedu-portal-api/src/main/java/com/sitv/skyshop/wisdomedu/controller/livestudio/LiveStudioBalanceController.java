/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.livestudio;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioBalanceInfo;
import com.sitv.skyshop.wisdomedu.service.livestudio.ILiveStudioBalanceService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("直播间账户接口")
@Validated
@RestController
@RequestMapping("/livestudiobalance")
public class LiveStudioBalanceController extends BaseRestController<ILiveStudioBalanceService, LiveStudioBalanceInfo> {

	@GetMapping("/livestudio/{liveStudioId}")
	public ResponseInfo<LiveStudioBalanceInfo> getBy(@PathVariable @Min(1) @NotBlank String liveStudioId) {
		return ResponseInfo.SUCCESS(service.getBy(Long.valueOf(liveStudioId)));
	}
}
