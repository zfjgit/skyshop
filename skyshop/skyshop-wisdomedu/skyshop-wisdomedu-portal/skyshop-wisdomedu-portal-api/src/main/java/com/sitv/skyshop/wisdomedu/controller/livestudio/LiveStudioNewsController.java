/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.livestudio;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioNewsInfo;
import com.sitv.skyshop.wisdomedu.service.livestudio.ILiveStudioNewsService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("直播间动态接口")
@Validated
@RestController
@RequestMapping("/livestudionews")
public class LiveStudioNewsController extends BaseRestController<ILiveStudioNewsService, LiveStudioNewsInfo> {

}
