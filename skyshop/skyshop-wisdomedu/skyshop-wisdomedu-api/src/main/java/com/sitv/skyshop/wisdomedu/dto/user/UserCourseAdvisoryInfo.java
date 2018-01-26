/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse;
import com.sitv.skyshop.wisdomedu.domain.user.UserCourseAdvisory;
import com.sitv.skyshop.wisdomedu.domain.user.UserCourseAdvisory.AdvisorySubjectType;
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
public class UserCourseAdvisoryInfo extends FullInfoDto {

	private static final long serialVersionUID = 5190764512455569596L;
	private CourseInfo course;
	private UserInfo user;
	private String content;
	private int praiseCount;
	private String reply;
	private Calendar replyTime;
	private EnumInfo<AdvisorySubjectType, String> courseType;

	public UserCourseAdvisoryInfo() {
	}

	public UserCourseAdvisoryInfo(Long id, CourseInfo course, EnumInfo<AdvisorySubjectType, String> courseType, UserInfo user, String content, int praiseCount, String reply,
	                Calendar createTime, Calendar replyTime) {
		super(id, createTime, null);
		this.course = course;
		this.user = user;
		this.content = content;
		this.praiseCount = praiseCount;
		this.reply = reply;
		this.replyTime = replyTime;
	}

	public static UserCourseAdvisoryInfo create(UserCourseAdvisory c) {
		if (c == null) {
			return null;
		}
		CourseInfo course = null;
		if (c.getCourseType() == AdvisorySubjectType.COURSE) {
			course = SingleCourseInfo.create((SingleCourse) c.getCourse());
		} else if (c.getCourseType() == AdvisorySubjectType.SERIESCOURSE) {
			course = SeriesCourseInfo.create((SeriesCourse) c.getCourse());
		}
		return new UserCourseAdvisoryInfo(c.getId(), course, EnumInfo.valueOf(AdvisorySubjectType.class, c.getCourseType().getCode()), UserInfo.create(c.getUser()), c.getContent(),
		                c.getPraiseCount(), c.getReply(), c.getCreateTime(), c.getReplyTime());
	}

	public static List<UserCourseAdvisoryInfo> creates(List<UserCourseAdvisory> list) {
		List<UserCourseAdvisoryInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (UserCourseAdvisory u : list) {
			if (u == null) {
				continue;
			}
			infos.add(UserCourseAdvisoryInfo.create(u));
		}
		return infos;
	}

}
