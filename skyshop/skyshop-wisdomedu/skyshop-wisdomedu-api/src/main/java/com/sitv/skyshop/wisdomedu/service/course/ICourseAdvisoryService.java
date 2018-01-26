/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.course;

import java.util.List;

import com.sitv.skyshop.wisdomedu.dto.course.CourseAdvisoryInfo;

/**
 * @author zfj20
 */
public interface ICourseAdvisoryService {

	List<CourseAdvisoryInfo> findCourseAdvisorys();

	List<CourseAdvisoryInfo> findSeriesAdvisoryCourses();

}
