/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.exception.EntityNotFoundException;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.course.ISeriesCourseCategoryDao;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioDao;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourseCategory;
import com.sitv.skyshop.wisdomedu.dto.course.SeriesCourseCategoryInfo;

/**
 * @author zfj20
 */
@Service
public class SeriesCourseCategoryService extends CrudService<ISeriesCourseCategoryDao, SeriesCourseCategory, SeriesCourseCategoryInfo> implements ISeriesCourseCategoryService {

	@Autowired
	private ILiveStudioDao liveStudioDao;

	public SeriesCourseCategoryInfo getOne(Long id) {
		return null;
	}

	public PageInfo<SeriesCourseCategoryInfo> search(SearchParamInfo<SeriesCourseCategoryInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<SeriesCourseCategoryInfo> categorys = SeriesCourseCategoryInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<SeriesCourseCategoryInfo> pageInfo = new com.github.pagehelper.PageInfo<>(categorys, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(SeriesCourseCategoryInfo t) {
		SeriesCourseCategory category = get(t.getId());
		if (category == null) {
			throw new EntityNotFoundException("" + t);
		}
		category.setName(t.getName());
		category.setOrder(t.getOrder());
		update(category);
	}

	public void createOne(SeriesCourseCategoryInfo t) {
		SeriesCourseCategory category = new SeriesCourseCategory();
		category.setLiveStudio(liveStudioDao.get(t.getLiveStudio().getId()));
		category.setName(t.getName());
		category.setOrder(t.getOrder());

		create(category);
	}

	public void sort(String[] ids) {
		List<Map<String, Object>> orders = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			Map<String, Object> order = new HashMap<>();
			order.put("id", ids[i]);
			order.put("order", i + 1);
			orders.add(order);

			dao.updateOrder(orders);
		}
	}

}
