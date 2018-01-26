/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.wisdomedu.dto.course.CourseAdvisoryInfo;
import com.sitv.skyshop.wisdomedu.service.course.ICourseAdvisoryService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("课程咨詢接口")
@Validated
@RestController
@RequestMapping("/courseadvisory")
public class CourseAdvisoryController {

	@Autowired
	private ICourseAdvisoryService courseAdvisoryService;

	@GetMapping("/")
	public ResponseInfo<Map<String, Object>> findCourse() {
		List<CourseAdvisoryInfo> courseAdvisoryInfos = courseAdvisoryService.findCourseAdvisorys();
		List<CourseAdvisoryInfo> seriesCourseAdvisoryInfos = courseAdvisoryService.findSeriesAdvisoryCourses();
		Map<String, Object> data = new HashMap<>();
		data.put("courseAdvisoryInfos", courseAdvisoryInfos);
		data.put("seriesCourseAdvisoryInfos", seriesCourseAdvisoryInfos);
		return ResponseInfo.SUCCESS(data);
	}
}
