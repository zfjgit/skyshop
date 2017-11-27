/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.ISIMCardDao;
import com.sitv.skyshop.massagechair.dao.device.ISIMCardOperatorDao;
import com.sitv.skyshop.massagechair.domain.device.SIMCard;
import com.sitv.skyshop.massagechair.domain.device.SIMCardOperator;
import com.sitv.skyshop.massagechair.dto.device.SIMCardInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月18日
 */
@Service
public class SIMCardService extends CrudService<ISIMCardDao, SIMCard, SIMCardInfo> implements ISIMCardService {

	private ISIMCardOperatorDao simCardOperatorDao;

	public SIMCardInfo getOne(Long id) {
		return SIMCardInfo.create(get(id));
	}

	public PageInfo<SIMCardInfo> search(SearchParamInfo<SIMCardInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<SIMCard> simCards = dao.search(q);

		com.github.pagehelper.PageInfo<SIMCard> pageInfo = new com.github.pagehelper.PageInfo<>(simCards, 5);

		List<SIMCardInfo> simCardOperators = SIMCardInfo.creates(pageInfo.getList());

		return new PageInfo<>(simCardOperators, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(SIMCardInfo t) {
		SIMCardOperator simCardOperator = simCardOperatorDao.get(t.getOperator().getId());
		SIMCard simCard = new SIMCard(t.getId(), t.getName(), t.getDescription(), t.getDataFlow(), t.getDueDate(), t.getSim(), simCardOperator);
		update(simCard);
	}

	public void createOne(SIMCardInfo t) {
		SIMCardOperator simCardOperator = simCardOperatorDao.get(t.getOperator().getId());
		SIMCard simCard = new SIMCard(t.getId(), t.getName(), t.getDescription(), t.getDataFlow(), t.getDueDate(), t.getSim(), simCardOperator);
		create(simCard);
	}

}
