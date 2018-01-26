/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.livestudio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioNews;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioNews.SubjectType;
import com.sitv.skyshop.wisdomedu.dto.course.CourseInfo;
import com.sitv.skyshop.wisdomedu.dto.course.SeriesCourseInfo;
import com.sitv.skyshop.wisdomedu.dto.course.SingleCourseInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioNewsInfo extends FullInfoDto {

	private static final long serialVersionUID = 7903715667732508424L;
	private LiveStudioInfo liveStudio;
	private String content;
	private String link;
	private Integer praiseCount;

	private EnumInfo<SubjectType, String> subjectType;
	private CourseInfo subject;

	private Long subjectId;

	public LiveStudioNewsInfo() {
	}

	public LiveStudioNewsInfo(Long id, LiveStudioInfo liveStudio, String content, String link, Integer praiseCount, EnumInfo<SubjectType, String> subjectType, CourseInfo subject,
	                Calendar createTime) {
		super(id, createTime, null);
		this.liveStudio = liveStudio;
		this.content = content;
		this.link = link;
		this.praiseCount = praiseCount;
		this.subjectType = subjectType;
		this.subject = subject;
	}

	public static LiveStudioNewsInfo create(LiveStudioNews c) {
		if (c == null) {
			return null;
		}
		CourseInfo subject = null;
		if (c.getSubjectType() == SubjectType.SERIESCOURSE) {
			subject = SeriesCourseInfo.create((SeriesCourse) c.getSubject());
		} else if (c.getSubjectType() == SubjectType.TOPIC) {
			subject = SingleCourseInfo.create((SingleCourse) c.getSubject());
		}
		return new LiveStudioNewsInfo(c.getId(), LiveStudioInfo.create(c.getLiveStudio()), c.getContent(), c.getLink(), c.getPraiseCount(),
		                EnumInfo.valueOf(SubjectType.class, c.getSubjectType().getCode()), subject, c.getCreateTime());
	}

	public static List<LiveStudioNewsInfo> creates(List<LiveStudioNews> list) {
		List<LiveStudioNewsInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (LiveStudioNews u : list) {
			if (u == null) {
				continue;
			}
			infos.add(LiveStudioNewsInfo.create(u));
		}
		return infos;
	}
}
