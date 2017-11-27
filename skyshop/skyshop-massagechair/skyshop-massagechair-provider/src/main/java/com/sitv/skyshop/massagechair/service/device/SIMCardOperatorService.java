/**
 *
 */
package com.sitv.skyshop.massagechair.service.device;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.dao.device.ISIMCardOperatorDao;
import com.sitv.skyshop.massagechair.domain.device.SIMCardOperator;
import com.sitv.skyshop.massagechair.dto.device.SIMCardOperatorInfo;
import com.sitv.skyshop.massagechair.service.device.ISIMCardOperatorService;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月17日
 */
@Service("simCardOperatorService")
public class SIMCardOperatorService extends CrudService<ISIMCardOperatorDao, SIMCardOperator, SIMCardOperatorInfo> implements ISIMCardOperatorService {

	public SIMCardOperatorInfo getOne(Long id) {
		return SIMCardOperatorInfo.create(get(id));
	}

	public PageInfo<SIMCardOperatorInfo> search(SearchParamInfo<SIMCardOperatorInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<SIMCardOperator> simCardOperators = dao.search(q);

		com.github.pagehelper.PageInfo<SIMCardOperator> pageInfo = new com.github.pagehelper.PageInfo<>(simCardOperators, 5);

		List<SIMCardOperatorInfo> simCardOperatorInfos = SIMCardOperatorInfo.creates(pageInfo.getList());

		return new PageInfo<>(simCardOperatorInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(SIMCardOperatorInfo t) {
		SIMCardOperator simCardOperator = new SIMCardOperator(t.getId(), t.getCode(), t.getName(), t.getDescription());
		update(simCardOperator);
	}

	public void createOne(SIMCardOperatorInfo t) {
		SIMCardOperator simCardOperator = new SIMCardOperator(t.getId(), t.getCode(), t.getName(), t.getDescription());
		create(simCardOperator);
	}

}
