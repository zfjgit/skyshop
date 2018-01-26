/**
 *
 */
package com.sitv.skyshop.tbataobao.check.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.tbataobao.dto.LoginUserInfo;
import com.sitv.skyshop.tbataobao.dto.SysCheckerInfo;
import com.sitv.skyshop.tbataobao.service.ISysCheckerService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Slf4j
@CrossOrigin
@Api("审核人员信息接口")
@Validated
@RestController
@RequestMapping("/syschecker")
public class SysCheckerController extends BaseRestController<ISysCheckerService, SysCheckerInfo> {

	@PostMapping("/login")
	public ResponseInfo<LoginUserInfo> login(@Valid @NotNull @ModelAttribute SysCheckerInfo userInfo) {
		log.debug("userInfo=" + userInfo);
		String sessionVerifyCode = request.getSession().getAttribute(com.sitv.skyshop.tbataobao.utils.Constants.SESSION_VERIFY_CODE_KEY) + "";
		if(Utils.isNull(sessionVerifyCode) || Utils.isNull(userInfo.getVerifycode()) || !sessionVerifyCode.equals(userInfo.getVerifycode())) {
			return ResponseInfo.VERIFYCODE_ERROR("验证码错误");
		}
		SysCheckerInfo info = service.login(userInfo);
		if (info != null) {
			LoginUserInfo sessionInfo = new LoginUserInfo(request.getRemoteAddr(), Utils.UUID(), request.getSession().getId(), info, System.currentTimeMillis());
			request.getSession().setAttribute(Constants.USER_KEY, sessionInfo);
			return ResponseInfo.SUCCESS(sessionInfo);
		}
		return ResponseInfo.USERNAME_PASSWORD_ERROR("账号或密码错误");
	}

	@DeleteMapping("/logout/{account}")
	public ResponseInfo<String> logout(@NotBlank @PathVariable String account) {
		service.logout(account);
		request.getSession().invalidate();
		return ResponseInfo.DELETED_SUCCESS("已退出登录");
	}
}
