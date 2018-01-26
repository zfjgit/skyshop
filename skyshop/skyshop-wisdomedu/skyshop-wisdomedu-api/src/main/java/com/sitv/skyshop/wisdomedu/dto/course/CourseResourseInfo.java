/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.course;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.course.CourseResource;
import com.sitv.skyshop.wisdomedu.domain.course.CourseResource.CourseResourceType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class CourseResourseInfo extends FullInfoDto {
	/**
	 *
	 */
	private static final long serialVersionUID = -41985198930980341L;

	private SingleCourseInfo course;
	private EnumInfo<CourseResourceType, String> resourceType;
	private String link;
	private Integer seconds;
	private Integer size;
	private String content;

	/**
	 *
	 */
	public CourseResourseInfo() {
	}

	public CourseResourseInfo(Long id, String name, String link, Integer seconds, Integer size, String content, SingleCourseInfo courseInfo,
	                EnumInfo<CourseResourceType, String> resourceType, Calendar createTime) {
		super(id, createTime, null);
		setName(name);
		this.link = link;
		this.seconds = seconds;
		this.size = size;
		this.content = content;
		this.course = courseInfo;
		this.resourceType = resourceType;
	}

	public static CourseResourseInfo create(CourseResource t) {
		if (t == null) {
			return null;
		}
		SingleCourseInfo courseInfo = SingleCourseInfo.create(t.getCourse());
		EnumInfo<CourseResourceType, String> resourceType = EnumInfo.valueOf(CourseResourceType.class, t.getResourceType().getCode());
		return new CourseResourseInfo(t.getId(), t.getName(), t.getLink(), t.getSeconds(), t.getSize(), t.getContent(), courseInfo, resourceType, t.getCreateTime());
	}

	public static List<CourseResourseInfo> creates(List<CourseResource> list) {
		List<CourseResourseInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (CourseResource courseResource : list) {
			if (courseResource == null) {
				continue;
			}
			infos.add(CourseResourseInfo.create(courseResource));
		}
		return infos;
	}
}
