/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.course.ISeriesCourseDao;
import com.sitv.skyshop.wisdomedu.dao.course.ISingleCourseDao;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioDao;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioNewsDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioNews;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioNews.SubjectType;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioNewsInfo;

/**
 * @author zfj20
 */
@Service
public class LiveStudioNewsService extends CrudService<ILiveStudioNewsDao, LiveStudioNews, LiveStudioNewsInfo> implements ILiveStudioNewsService {

	@Autowired
	private ILiveStudioDao liveStudioDao;

	@Autowired
	private ISingleCourseDao courseDao;

	@Autowired
	private ISeriesCourseDao seriesCourseDao;

	public LiveStudioNewsInfo getOne(Long id) {
		return null;
	}

	public PageInfo<LiveStudioNewsInfo> search(SearchParamInfo<LiveStudioNewsInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<LiveStudioNewsInfo> infos = LiveStudioNewsInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<LiveStudioNewsInfo> pageInfo = new com.github.pagehelper.PageInfo<>(infos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(LiveStudioNewsInfo t) {
	}

	public void createOne(LiveStudioNewsInfo t) {
		LiveStudioNews news = new LiveStudioNews();
		news.setContent(t.getContent());
		news.setCreateTime(Calendar.getInstance());
		news.setLiveStudio(liveStudioDao.get(t.getLiveStudio().getId()));
		news.setPraiseCount(0);
		news.setSubjectType(BaseEnum.valueOf(SubjectType.class, t.getSubjectType().getCode()));
		if (news.getSubjectType() == SubjectType.TOPIC) {
			news.setSubject(courseDao.get(t.getSubjectId()));
		} else {
			news.setSubject(seriesCourseDao.get(t.getSubjectId()));
		}

		create(news);
	}

}
