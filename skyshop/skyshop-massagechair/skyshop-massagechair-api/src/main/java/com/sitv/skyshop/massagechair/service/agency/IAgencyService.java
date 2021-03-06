/**
 *
 */
package com.sitv.skyshop.massagechair.service.agency;

import com.sitv.skyshop.massagechair.dto.agency.AgencyInfo;
import com.sitv.skyshop.massagechair.dto.agency.AgencyOverviewInfo;
import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.service.IDeleteStatusService;

/**
 * @author zfj20 @ 2017年12月5日
 */
public interface IAgencyService extends IBaseService<AgencyInfo>, IDeleteStatusService<AgencyInfo> {

	String OVERVIEW_KEY = "OVERVIEW_KEY";
	String OVERVIEW_LAST_GET_TIME_KEY = "OVERVIEW_LAST_GET_TIME_KEY";

	AgencyOverviewInfo getOverview(Long agencyId);
}
