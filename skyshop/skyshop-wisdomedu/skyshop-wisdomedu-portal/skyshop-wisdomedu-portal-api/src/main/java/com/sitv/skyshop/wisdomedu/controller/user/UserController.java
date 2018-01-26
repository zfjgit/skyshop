/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.wisdomedu.dto.user.UserInfo;
import com.sitv.skyshop.wisdomedu.service.user.IUserService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("用户信息接口")
@Validated
@RestController
@RequestMapping("/user")
public class UserController extends BaseRestController<IUserService, UserInfo> {

}
