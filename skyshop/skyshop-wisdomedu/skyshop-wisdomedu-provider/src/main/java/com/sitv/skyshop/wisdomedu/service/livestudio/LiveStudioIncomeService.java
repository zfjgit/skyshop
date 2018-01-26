/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioIncomesDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioIncomes;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioIncomesInfo;

/**
 * @author zfj20
 */
@Service
public class LiveStudioIncomeService extends CrudService<ILiveStudioIncomesDao, LiveStudioIncomes, LiveStudioIncomesInfo> implements ILiveStudioIncomeService {

	public LiveStudioIncomesInfo getOne(Long id) {
		return null;
	}

	public PageInfo<LiveStudioIncomesInfo> search(SearchParamInfo<LiveStudioIncomesInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<LiveStudioIncomesInfo> infos = LiveStudioIncomesInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<LiveStudioIncomesInfo> pageInfo = new com.github.pagehelper.PageInfo<>(infos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(LiveStudioIncomesInfo t) {
	}

	public void createOne(LiveStudioIncomesInfo t) {
	}

}
