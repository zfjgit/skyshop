/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.massagechair.dto.user.PermissionInfo;
import com.sitv.skyshop.massagechair.service.user.IPermissionService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Api("权限接口")
@Validated
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseRestController<IPermissionService, PermissionInfo> {

}
