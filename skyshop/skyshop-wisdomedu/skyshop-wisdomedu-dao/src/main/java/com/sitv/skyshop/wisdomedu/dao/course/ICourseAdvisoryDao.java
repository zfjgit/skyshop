/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.course;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.wisdomedu.domain.user.UserCourseAdvisory;
import com.sitv.skyshop.wisdomedu.dto.course.CourseAdvisoryInfo;

/**
 * @author zfj20
 */
@MyBatisDao
public interface ICourseAdvisoryDao {

	List<CourseAdvisoryInfo> findCourseAdvisorys(@Param("advisorySubjectType") UserCourseAdvisory.AdvisorySubjectType advisorySubjectType);

}
