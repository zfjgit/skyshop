/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.wisdomedu.dto.user.UserLiveStudioInfo;
import com.sitv.skyshop.wisdomedu.service.user.IUserLiveStudioService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("用户直播間信息接口")
@Validated
@RestController
@RequestMapping("/userlivestudio")
public class UserLiveStudioController extends BaseRestController<IUserLiveStudioService, UserLiveStudioInfo> {

}
