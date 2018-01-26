/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.livestudio;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioIncomesInfo;
import com.sitv.skyshop.wisdomedu.service.livestudio.ILiveStudioIncomeService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("直播间收益接口")
@Validated
@RestController
@RequestMapping("/livestudioincomes")
public class LiveStudioIncomesController extends BaseRestController<ILiveStudioIncomeService, LiveStudioIncomesInfo> {

}
