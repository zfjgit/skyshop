/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.agency;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.massagechair.dto.agency.AgencyWithrawInfo;
import com.sitv.skyshop.massagechair.service.agency.IAgencyWithrawService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Api("代理商提现信息接口")
@Validated
@RestController
@RequestMapping("/agencywithraw")
public class AgencyWithrawController extends BaseRestController<IAgencyWithrawService, AgencyWithrawInfo> {

}
