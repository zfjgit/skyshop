/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;
import com.sitv.skyshop.massagechair.service.user.IUserService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月24日
 */
@Api("用戶信息查询接口")
@Validated
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<IUserService<UserInfo>, UserInfo> {

	public ResponseInfo<UserInfo> create(UserInfo info) {
		return ResponseInfo.FORBIDDEN_ERROR("错误的接口调用");
	}

	public ResponseInfo<UserInfo> update(UserInfo info) {
		return ResponseInfo.FORBIDDEN_ERROR("错误的接口调用");
	}
}
