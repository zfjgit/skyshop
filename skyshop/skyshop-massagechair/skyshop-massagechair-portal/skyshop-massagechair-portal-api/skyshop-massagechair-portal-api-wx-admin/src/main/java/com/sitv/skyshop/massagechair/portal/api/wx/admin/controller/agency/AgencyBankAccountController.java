/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.agency;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseController;
import com.sitv.skyshop.massagechair.dto.agency.AgencyBankAccountInfo;
import com.sitv.skyshop.massagechair.service.agency.IAgencyBankAccountService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Api("代理商银行信息接口")
@Validated
@RestController
@RequestMapping("/agencybank")
public class AgencyBankAccountController extends BaseController<IAgencyBankAccountService, AgencyBankAccountInfo> {

}
