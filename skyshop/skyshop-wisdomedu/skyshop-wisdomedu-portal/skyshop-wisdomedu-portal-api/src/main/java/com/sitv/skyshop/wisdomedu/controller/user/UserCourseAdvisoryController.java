/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.wisdomedu.dto.user.UserCourseAdvisoryInfo;
import com.sitv.skyshop.wisdomedu.service.user.IUserCourseAdvisoryService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("用户咨询接口")
@Validated
@RestController
@RequestMapping("/usercourseadvisory")
public class UserCourseAdvisoryController extends BaseRestController<IUserCourseAdvisoryService, UserCourseAdvisoryInfo> {

	@PutMapping("/reply")
	public ResponseInfo<String> reply(@Valid @NotNull @ModelAttribute UserCourseAdvisoryInfo info) {
		service.reply(info);
		return ResponseInfo.SUCCESS("");
	}
}
