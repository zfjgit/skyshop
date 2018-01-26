/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.livestudio;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.dto.UserSessionInfo;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;
import com.sitv.skyshop.wisdomedu.dto.user.LoginUserInfo;
import com.sitv.skyshop.wisdomedu.service.livestudio.ILiveStudioService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 */
@Slf4j
@Api("直播间接口")
@Validated
@RestController
@RequestMapping("/livestudio")
public class LiveStudioController extends BaseRestController<ILiveStudioService, LiveStudioInfo> {

	@Autowired
	private ILiveStudioService liveStudioService;

	@GetMapping("/{id}")
	public ResponseInfo<LiveStudioInfo> get(@PathVariable @Min(1) @NotBlank String id) {
		log.debug("id=" + id);

		LiveStudioInfo liveStudioInfo = liveStudioService.getOne(Long.valueOf(id));
		if (liveStudioInfo == null) {
			return ResponseInfo.NOT_FOUND_ERROR("没有找到对应的直播间信息");
		}

		int followCount = liveStudioService.getFollowCount(liveStudioInfo.getId());

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		log.debug("c=" + Utils.time2String(c));
		BigDecimal income = liveStudioService.getIncome(liveStudioInfo.getId(), Utils.getDayStartTime(c), Utils.getDayEndTime(c));

		liveStudioInfo.setIncome(income);
		liveStudioInfo.setFollowCount(followCount);

		UserSessionInfo sessionInfo = new LoginUserInfo("", Utils.UUID(), request.getSession().getId(), liveStudioInfo, System.currentTimeMillis());
		request.getSession().setAttribute(Constants.USER_KEY, sessionInfo);

		return ResponseInfo.SUCCESS(liveStudioInfo);
	}

	@PutMapping("/updateheadimg")
	public ResponseInfo<String> updateHeadimg(@NotNull @Valid @ModelAttribute LiveStudioInfo info) {
		service.updateHeadimg(info.getId(), info.getHeadimg());
		return ResponseInfo.SUCCESS("");
	}

	@PutMapping("/updatename")
	public ResponseInfo<String> updateName(@NotNull @Valid @ModelAttribute LiveStudioInfo info) {
		service.updateName(info.getId(), info.getName());
		return ResponseInfo.SUCCESS("");
	}

	@PutMapping("/updateintroduction")
	public ResponseInfo<String> updateIntroduction(@NotNull @Valid @ModelAttribute LiveStudioInfo info) {
		service.updateIntroduction(info.getId(), info.getIntroduction());
		return ResponseInfo.SUCCESS("");
	}

	@PutMapping("/updatebackgroundimg")
	public ResponseInfo<String> updateBackgroundimg(@NotNull @Valid @ModelAttribute LiveStudioInfo info) {
		service.updateBackgroundimg(info.getId(), info.getBackgroundimg());
		return ResponseInfo.SUCCESS("");
	}
}
