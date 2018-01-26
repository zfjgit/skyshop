/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.livestudio;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioWithrawRecordInfo;
import com.sitv.skyshop.wisdomedu.service.livestudio.ILiveStudioWithrawRecordService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("直播间提现记录接口")
@Validated
@RestController
@RequestMapping("/livestudiowithrawrecord")
public class LiveStudioWithrawRecordController extends BaseRestController<ILiveStudioWithrawRecordService, LiveStudioWithrawRecordInfo> {

}
