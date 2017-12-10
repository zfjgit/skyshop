package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.user;

import java.util.Calendar;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.interceptor.auth.annotation.AuthorizationRequired;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.massagechair.domain.record.UserOperateRecord.OperateType;
import com.sitv.skyshop.massagechair.dto.agency.AgencyUserInfo;
import com.sitv.skyshop.massagechair.dto.record.UserOperateRecordInfo;
import com.sitv.skyshop.massagechair.dto.user.LoginUserInfo;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;
import com.sitv.skyshop.massagechair.service.user.IUserService;
import com.sitv.skyshop.massagechair.service.userecord.IOperateRecordService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月7日
 */
@Slf4j
@Api("账号登录接口")
@Validated
@RestController
@RequestMapping("/loginuser")
public class LoginUserController extends BaseRestController<IUserService<UserInfo>, UserInfo> {

	@Autowired
	private IOperateRecordService recordService;

	@PostMapping("/")
	@AuthorizationRequired(false)
	public ResponseInfo<UserInfo> create(@Valid @NotNull @ModelAttribute UserInfo userInfo) {
		log.debug("账号登录>>>>");
		log.debug("login info=" + userInfo);

		userInfo = service.login(userInfo.getCode(), userInfo.getPassword());

		LoginUserInfo loginUserInfo = new LoginUserInfo(request.getRemoteHost(), userInfo, Utils.UUID(), System.currentTimeMillis());
		request.getSession().setAttribute(Constants.USER_KEY, loginUserInfo);

		log.debug("账号登录成功");
		log.debug("user info=" + loginUserInfo);

		UserOperateRecordInfo record = null;
		if (userInfo instanceof AgencyUserInfo) {
			record = new UserOperateRecordInfo(((AgencyUserInfo) userInfo).getAgency(), userInfo.getCode(), request.getRemoteHost(),
					EnumInfo.valueOf(OperateType.class, IOperateRecordService.AGENCY), "代理商登录成功", Calendar.getInstance());
		} else {
			record = new UserOperateRecordInfo(null, userInfo.getCode(), request.getRemoteHost(), EnumInfo.valueOf(OperateType.class, IOperateRecordService.SYSTEM), "管理员登录成功",
					Calendar.getInstance());
		}
		recordService.createOne(record);
		return ResponseInfo.SUCCESS(loginUserInfo);
	}

	@DeleteMapping("/{id}")
	public ResponseInfo<UserInfo> delete(@NotBlank @Min(0) @PathVariable String id) {
		log.debug("退出登录>>>");
		log.debug("userid=" + id);

		if (request.getSession() == null) {
			return ResponseInfo.DELETED_SUCCESS("退出登录成功");
		}

		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(Constants.USER_KEY);
		request.getSession().invalidate();

		if (loginUserInfo == null) {
			return ResponseInfo.DELETED_SUCCESS("退出登录成功");
		}

		UserInfo userInfo = loginUserInfo.getUserInfo();

		UserOperateRecordInfo record = null;
		if (userInfo instanceof AgencyUserInfo) {
			record = new UserOperateRecordInfo(((AgencyUserInfo) userInfo).getAgency(), userInfo.getCode(), request.getRemoteHost(),
					EnumInfo.valueOf(OperateType.class, IOperateRecordService.AGENCY), "代理商退出登录成功", Calendar.getInstance());
		} else {
			record = new UserOperateRecordInfo(null, userInfo.getCode(), request.getRemoteHost(), EnumInfo.valueOf(OperateType.class, IOperateRecordService.SYSTEM), "管理员退出登录成功",
					Calendar.getInstance());
		}
		recordService.createOne(record);
		return ResponseInfo.DELETED_SUCCESS("退出登录成功");
	}
}
