/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.user.ManagerInfo;
import com.sitv.skyshop.massagechair.service.user.IManagerService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("管理员接口")
@Validated
@RestController
@RequestMapping("/manager")
public class ManagerController extends BaseRestController<IManagerService, ManagerInfo> {

	@RequestMapping(value = "/authorize/{id}/{roles}", method = RequestMethod.POST)
	public ResponseInfo<String> authorize(@PathVariable @NotBlank @DecimalMin("1") String id, @PathVariable @NotBlank String roles) {
		StringTokenizer stringTokenizer = new StringTokenizer(roles, ",");
		List<Long> roleIds = new ArrayList<>();
		while (stringTokenizer.hasMoreTokens()) {
			Long roleId = Long.valueOf(stringTokenizer.nextToken());
			roleIds.add(roleId);
		}
		service.authorize(Long.valueOf(id), roleIds);
		return ResponseInfo.CREATED_SUCCESS("授权成功");
	}
}
