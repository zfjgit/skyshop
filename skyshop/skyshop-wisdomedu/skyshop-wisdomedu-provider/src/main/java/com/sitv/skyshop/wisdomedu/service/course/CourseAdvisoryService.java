/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitv.skyshop.wisdomedu.dao.course.ICourseAdvisoryDao;
import com.sitv.skyshop.wisdomedu.domain.user.UserCourseAdvisory.AdvisorySubjectType;
import com.sitv.skyshop.wisdomedu.dto.course.CourseAdvisoryInfo;

/**
 * @author zfj20
 */
@Service
public class CourseAdvisoryService implements ICourseAdvisoryService {

	@Autowired
	private ICourseAdvisoryDao dao;

	public List<CourseAdvisoryInfo> findCourseAdvisorys() {
		return dao.findCourseAdvisorys(AdvisorySubjectType.COURSE);
	}

	public List<CourseAdvisoryInfo> findSeriesAdvisoryCourses() {
		return dao.findCourseAdvisorys(AdvisorySubjectType.SERIESCOURSE);
	}

}
