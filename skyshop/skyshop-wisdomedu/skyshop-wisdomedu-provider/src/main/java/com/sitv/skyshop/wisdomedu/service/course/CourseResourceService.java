/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.course;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.course.ICourseResourceDao;
import com.sitv.skyshop.wisdomedu.dao.course.ISingleCourseDao;
import com.sitv.skyshop.wisdomedu.domain.course.CourseResource;
import com.sitv.skyshop.wisdomedu.domain.course.CourseResource.CourseResourceType;
import com.sitv.skyshop.wisdomedu.dto.course.CourseResourseInfo;

/**
 * @author zfj20
 */
@Service
public class CourseResourceService extends CrudService<ICourseResourceDao, CourseResource, CourseResourseInfo> implements ICourseResourceService {

	@Autowired
	private ISingleCourseDao courseDao;

	public CourseResourseInfo getOne(Long id) {
		return null;
	}

	public PageInfo<CourseResourseInfo> search(SearchParamInfo<CourseResourseInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<CourseResourseInfo> infos = CourseResourseInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<CourseResourseInfo> pageInfo = new com.github.pagehelper.PageInfo<>(infos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(CourseResourseInfo t) {
	}

	public void createOne(CourseResourseInfo t) {
		CourseResource courseResource = new CourseResource();
		courseResource.setContent(t.getContent());
		courseResource.setLink(t.getLink());
		courseResource.setCourse(courseDao.get(t.getCourse().getId()));
		courseResource.setSeconds(t.getSeconds());
		courseResource.setSize(t.getSize());
		courseResource.setName(t.getName());
		courseResource.setCreateTime(Calendar.getInstance());

		String suffix = "";
		if (!Utils.isNull(t.getName()) && t.getName().lastIndexOf(".") > 0) {
			suffix = t.getName().substring(t.getName().lastIndexOf(".")).toLowerCase();
		}
		if (!"".equals(suffix)) {
			if (suffix.endsWith(".ppt") || suffix.endsWith(".pptx")) {
				courseResource.setResourceType(CourseResourceType.PPT);
			} else if (suffix.endsWith(".avi") || suffix.endsWith(".mp4") || suffix.endsWith(".wmv") || suffix.endsWith(".3gp") || suffix.endsWith(".mkv")
			                || suffix.endsWith(".mpg") || suffix.endsWith(".flv") || suffix.endsWith(".swf") || suffix.endsWith("rmvb")) {
				courseResource.setResourceType(CourseResourceType.VIDEO);
			} else if (suffix.endsWith(".jpg") || suffix.endsWith(".jpeg") || suffix.endsWith(".png") || suffix.endsWith(".gif") || suffix.endsWith(".tiff")
			                || suffix.endsWith(".bmp")) {
				courseResource.setResourceType(CourseResourceType.IMG);
			} else if (suffix.endsWith(".wav") || suffix.endsWith(".mp3") || suffix.endsWith(".aac") || suffix.endsWith(".wma") || suffix.endsWith(".flac")
			                || suffix.endsWith(".ogg")) {
				courseResource.setResourceType(CourseResourceType.AUDIO);
			}
		} else {
			courseResource.setResourceType(CourseResourceType.TEXT);
		}

		create(courseResource);

		t.setResourceType(EnumInfo.valueOf(CourseResourceType.class, courseResource.getResourceType().getCode()));
		t.setId(courseResource.getId());
	}

}
