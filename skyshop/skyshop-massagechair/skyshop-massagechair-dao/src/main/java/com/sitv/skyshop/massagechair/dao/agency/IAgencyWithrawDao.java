/**
 *
 */
package com.sitv.skyshop.massagechair.dao.agency;

import java.math.BigDecimal;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.domain.agency.AgencyWithraw;
import com.sitv.skyshop.massagechair.dto.agency.AgencyWithrawInfo;

/**
 * @author zfj20 @ 2017年12月5日
 */
@MyBatisDao
public interface IAgencyWithrawDao extends ICrudDao<AgencyWithraw> {
	void updateStatus(AgencyWithraw withraw);

	BigDecimal getWithrawTotalMoney(SearchParamInfo<AgencyWithrawInfo> q);
}
