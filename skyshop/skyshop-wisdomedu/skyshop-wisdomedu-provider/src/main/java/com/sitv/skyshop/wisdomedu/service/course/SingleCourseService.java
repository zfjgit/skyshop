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
import com.sitv.skyshop.wisdomedu.dao.course.ISeriesCourseDao;
import com.sitv.skyshop.wisdomedu.dao.course.ISingleCourseDao;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioDao;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.CourseForm;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.JoinSeriesCourseStatus;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.LiveType;
import com.sitv.skyshop.wisdomedu.dto.course.SingleCourseInfo;

/**
 * @author zfj20
 */
@Service
public class SingleCourseService extends CrudService<ISingleCourseDao, SingleCourse, SingleCourseInfo> implements ISingleCourseService {

	@Autowired
	private ILiveStudioDao liveStudioDao;

	@Autowired
	private ISeriesCourseDao seriesCourseDao;

	public SingleCourseInfo getOne(Long id) {
		return SingleCourseInfo.create(get(id));
	}

	public PageInfo<SingleCourseInfo> search(SearchParamInfo<SingleCourseInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<SingleCourseInfo> courseInfos = SingleCourseInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<SingleCourseInfo> pageInfo = new com.github.pagehelper.PageInfo<>(courseInfos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(SingleCourseInfo t) {
		SingleCourse course = get(t.getId());
		if (course == null) {
			throw new EntityNotFoundException("" + t);
		}
		course.setCourseForm(BaseEnum.valueOf(CourseForm.class, t.getCourseForm().getCode()));
		course.setSilenceMode(t.isSilenceMode());
		course.setTitle(t.getTitle());
		course.setIntroduction(t.getIntroduction());
		course.setPasswd(t.getPasswd());
		course.setStartTime(t.getStartTime());
		course.setHeadimg(t.getHeadimg());
		course.setShowDetailPage(t.isShowDetailPage());
		course.setTicketsMoney(t.getTicketsMoney());
		course.setSpecialOffer(t.getTicketsMoney());
		course.setLiveStudio(liveStudioDao.get(t.getLiveStudio().getId()));
		course.setLiveType(BaseEnum.valueOf(LiveType.class, t.getLiveType().getCode()));

		if (t.getSeriesCourse() != null) {
			course.setSeriesCourse(seriesCourseDao.get(t.getSeriesCourse().getId()));
			course.setJoinSeriesCourseStatus(JoinSeriesCourseStatus.JOINED);
		} else {
			course.setJoinSeriesCourseStatus(JoinSeriesCourseStatus.NOT_JOIN);
		}

		update(course);
	}

	public void createOne(SingleCourseInfo t) {
		SingleCourse course = new SingleCourse();
		course.setCourseForm(BaseEnum.valueOf(CourseForm.class, t.getCourseForm().getCode()));
		course.setEnd(false);
		course.setSilenceMode(false);
		course.setTitle(t.getTitle());
		course.setPasswd(t.getPasswd());
		course.setStartTime(t.getStartTime());
		course.setHeadimg(t.getHeadimg());
		course.setShowDetailPage(t.isShowDetailPage());
		course.setTicketsMoney(t.getTicketsMoney());
		course.setSpecialOffer(t.getTicketsMoney());
		course.setCreateTime(Calendar.getInstance());
		course.setLiveStudio(liveStudioDao.get(t.getLiveStudio().getId()));
		course.setLiveType(BaseEnum.valueOf(LiveType.class, t.getLiveType().getCode()));
		course.setJoinSeriesCourseStatus(JoinSeriesCourseStatus.NOT_JOIN);

		create(course);
	}

}
