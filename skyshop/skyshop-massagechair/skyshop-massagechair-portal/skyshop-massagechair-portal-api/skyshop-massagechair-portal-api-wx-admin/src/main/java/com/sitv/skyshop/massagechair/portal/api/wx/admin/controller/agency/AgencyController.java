/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.agency;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.agency.AgencyOverviewInfo;
import com.sitv.skyshop.massagechair.service.agency.IAgencyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月5日
 */
@Slf4j
@Api("代理商接口")
@Validated
@RestController
@RequestMapping("/agency")
public class AgencyController extends BaseRestController<IAgencyService, AgencyInfo> {

	@Autowired
	private Environment env;

	@ApiOperation(value = "", notes = "如果返回HttpStatus_Code=403，请检查路径映射是否配置了@PathVariable参数")
	@GetMapping("/overview/{agencyId}")
	public ResponseInfo<AgencyOverviewInfo> overview(@Min(0) @PathVariable Long agencyId) {
		log.debug("账号资产/收益/订单信息总览>>>");
		log.debug("agencyId=" + agencyId);

		HttpSession session = request.getSession();

		AgencyOverviewInfo overviewInfo = (AgencyOverviewInfo) session.getAttribute(IAgencyService.OVERVIEW_KEY);
		Long lastGetTime = (Long) session.getAttribute(IAgencyService.OVERVIEW_LAST_GET_TIME_KEY);
		if (overviewInfo == null || System.currentTimeMillis() - lastGetTime > Long.valueOf(env.getProperty(Constants.AGENCY_OVERVIEW_EXPIRED_TIME))) {
			overviewInfo = service.getOverview(agencyId);
			session.setAttribute(IAgencyService.OVERVIEW_KEY, overviewInfo);
			session.setAttribute(IAgencyService.OVERVIEW_LAST_GET_TIME_KEY, System.currentTimeMillis());
		}

		log.debug("overviewInfo=" + overviewInfo);
		return ResponseInfo.SUCCESS(overviewInfo);
	}

}
