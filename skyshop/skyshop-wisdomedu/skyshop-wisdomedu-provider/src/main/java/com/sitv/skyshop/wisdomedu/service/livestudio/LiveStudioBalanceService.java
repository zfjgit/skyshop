/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioBalanceDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioBalance;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioBalanceInfo;

/**
 * @author zfj20
 */
@Service
public class LiveStudioBalanceService extends CrudService<ILiveStudioBalanceDao, LiveStudioBalance, LiveStudioBalanceInfo> implements ILiveStudioBalanceService {

	public LiveStudioBalanceInfo getOne(Long id) {
		return null;
	}

	public PageInfo<LiveStudioBalanceInfo> search(SearchParamInfo<LiveStudioBalanceInfo> q) {
		return null;
	}

	public void updateOne(LiveStudioBalanceInfo t) {
	}

	public void createOne(LiveStudioBalanceInfo t) {
	}

	public LiveStudioBalanceInfo getBy(Long liveStudioId) {
		LiveStudioBalance balance = dao.getBy(liveStudioId);
		return LiveStudioBalanceInfo.create(balance);
	}

}
