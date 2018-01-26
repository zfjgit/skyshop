/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.course;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.exception.EntityNotFoundException;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.course.ISeriesCourseCategoryDao;
import com.sitv.skyshop.wisdomedu.dao.course.ISeriesCourseDao;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioDao;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse.ChargeType;
import com.sitv.skyshop.wisdomedu.dto.course.SeriesCourseInfo;

/**
 * @author zfj20
 */
@Service
public class SeriesCourseService extends CrudService<ISeriesCourseDao, SeriesCourse, SeriesCourseInfo> implements ISeriesCourseService {

	@Autowired
	private ISeriesCourseCategoryDao seriesCourseCategoryDao;

	@Autowired
	private ILiveStudioDao liveStudioDao;

	public SeriesCourseInfo getOne(Long id) {
		return SeriesCourseInfo.create(get(id));
	}

	public PageInfo<SeriesCourseInfo> search(SearchParamInfo<SeriesCourseInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<SeriesCourseInfo> infos = SeriesCourseInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<SeriesCourseInfo> pageInfo = new com.github.pagehelper.PageInfo<>(infos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(SeriesCourseInfo t) {
		SeriesCourse seriesCourse = get(t.getId());
		if (seriesCourse == null) {
			throw new EntityNotFoundException("" + t);
		}
		if (t.getCategory() != null) {
			seriesCourse.setCategory(seriesCourseCategoryDao.get(t.getCategory().getId()));
		}
		seriesCourse.setChargeType(BaseEnum.valueOf(ChargeType.class, t.getChargeType().getCode()));
		seriesCourse.setCount(t.getCount());
		seriesCourse.setHeadimg(t.getHeadimg());
		seriesCourse.setLiveStudio(liveStudioDao.get(t.getLiveStudio().getId()));
		seriesCourse.setSpecialOffer(t.getTicketsMoney());
		seriesCourse.setTicketsMoney(t.getTicketsMoney());
		seriesCourse.setTitle(t.getTitle());
		seriesCourse.setDistribution(false);
		seriesCourse.setDistributionPercent(0);
		seriesCourse.setIntroduction(t.getIntroduction());

		update(seriesCourse);
	}

	public void createOne(SeriesCourseInfo t) {
		SeriesCourse seriesCourse = new SeriesCourse();
		if (t.getCategory() != null) {
			seriesCourse.setCategory(seriesCourseCategoryDao.get(t.getCategory().getId()));
		}
		seriesCourse.setChargeType(BaseEnum.valueOf(ChargeType.class, t.getChargeType().getCode()));
		seriesCourse.setCount(t.getCount());
		seriesCourse.setCreateTime(Calendar.getInstance());
		seriesCourse.setHeadimg(t.getHeadimg());
		seriesCourse.setLiveStudio(liveStudioDao.get(t.getLiveStudio().getId()));
		seriesCourse.setSpecialOffer(t.getTicketsMoney());
		seriesCourse.setTicketsMoney(t.getTicketsMoney());
		seriesCourse.setTitle(t.getTitle());
		seriesCourse.setDistribution(false);
		seriesCourse.setDistributionPercent(0);
		seriesCourse.setIntroduction(t.getIntroduction());

		create(seriesCourse);
	}

}
