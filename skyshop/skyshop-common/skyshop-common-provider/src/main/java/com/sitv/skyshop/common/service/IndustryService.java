/**
 *
 */
package com.sitv.skyshop.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.dao.IIndustryDao;
import com.sitv.skyshop.common.domain.Industry;
import com.sitv.skyshop.common.dto.IndustryInfo;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Service("industryService")
public class IndustryService extends CrudService<IIndustryDao, Industry, IndustryInfo> implements IBaseService<IndustryInfo> {

	public IndustryInfo getOne(Long id) {
		return IndustryInfo.create(get(id));
	}

	public PageInfo<IndustryInfo> search(SearchParamInfo<IndustryInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<Industry> industries = super.dao.search(q);

		com.github.pagehelper.PageInfo<Industry> pageInfo = new com.github.pagehelper.PageInfo<>(industries, 5);

		List<IndustryInfo> industryInfos = IndustryInfo.creates(pageInfo.getList());

		return new PageInfo<>(industryInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void deleteOne(Long id) {
		delete(id);
	}

	public void updateOne(IndustryInfo t) {
		Industry industry = get(t.getId());

		industry.setCode(t.getCode());
		industry.setName(t.getName());

		update(industry);
	}

	public void createOne(IndustryInfo t) {
		Industry industry = new Industry(t.getName(), t.getCode());

		create(industry);
	}

}
