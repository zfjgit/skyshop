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

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.user.RoleInfo;
import com.sitv.skyshop.massagechair.service.user.IRoleService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("角色接口")
@Validated
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<IRoleService, RoleInfo> {

	@RequestMapping(value = "/authorize/{id}/{permissions}", method = RequestMethod.POST)
	public ResponseInfo<String> authorize(@PathVariable @NotBlank @DecimalMin("1") String id, @PathVariable @NotBlank String permissions) {
		StringTokenizer stringTokenizer = new StringTokenizer(permissions, ",");
		List<Long> permissionIds = new ArrayList<>();
		while (stringTokenizer.hasMoreTokens()) {
			Long permissionId = Long.valueOf(stringTokenizer.nextToken());
			permissionIds.add(permissionId);
		}
		service.authorize(Long.valueOf(id), permissionIds);
		return ResponseInfo.CREATED_SUCCESS("授权成功");
	}
}
