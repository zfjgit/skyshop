/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.course;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sitv.skyshop.dto.info.EnumInfo;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.CourseForm;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.LiveType;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 */
@Getter
@Setter
@ToString
public class SingleCourseInfo extends CourseInfo {

	private static final long serialVersionUID = -6941869785567059049L;

	private LiveStudioInfo liveStudio;

	private String title;
	private String headimg;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Calendar startTime;
	private String presenter;
	private String presenterIntroduction;
	private boolean isSilenceMode;
	private String introduction;
	private boolean isShowDetailPage;
	private String passwd;
	private BigDecimal ticketsMoney;
	private boolean isDistribution;
	private Integer distributionPercent;

	private EnumInfo<LiveType, String> liveType;
	private EnumInfo<CourseForm, String> courseForm;

	private SeriesCourseInfo seriesCourse;

	private BigDecimal specialOffer;
	private boolean isEnd;

	private int buyCount;

	public SingleCourseInfo() {
	}

	public SingleCourseInfo(Long id, Calendar createTime) {
		super(id, createTime);
	}

	public static SingleCourseInfo create(SingleCourse c) {
		if (c == null) {
			return null;
		}
		return new SingleCourseInfo(c.getId(), LiveStudioInfo.create(c.getLiveStudio()), c.getTitle(), c.getHeadimg(), c.getStartTime(), c.getPresenter(),
		                c.getPresenterIntroduction(), c.isSilenceMode(), c.getIntroduction(), c.isShowDetailPage(), c.getPasswd(), c.getTicketsMoney(), c.isDistribution(),
		                c.getDistributionPercent(), EnumInfo.valueOf(LiveType.class, c.getLiveType().getCode()), EnumInfo.valueOf(CourseForm.class, c.getCourseForm().getCode()),
		                SeriesCourseInfo.create(c.getSeriesCourse()), c.getSpecialOffer(), c.isEnd(), c.getBuyCount(), c.getCreateTime());
	}

	public static List<SingleCourseInfo> creates(List<SingleCourse> list) {
		List<SingleCourseInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (SingleCourse u : list) {
			if (u == null) {
				continue;
			}
			if (u instanceof SingleCourse) {
				infos.add(SingleCourseInfo.create(u));
			}
		}
		return infos;
	}

	public SingleCourseInfo(Long id, LiveStudioInfo liveStudio, String title, String headimg, Calendar startTime, String presenter, String presenterIntroducation,
	                boolean isSilenceMode, String introduction, boolean isShowDetailPage, String passwd, BigDecimal ticketsMoney, boolean isDistribution,
	                Integer distributionPercent, EnumInfo<LiveType, String> liveType, EnumInfo<CourseForm, String> courseForm, SeriesCourseInfo seriesCourse,
	                BigDecimal specialOffer, boolean isEnd, int buyCount, Calendar createTime) {
		super(id, createTime);
		this.liveStudio = liveStudio;
		this.title = title;
		this.headimg = headimg;
		this.startTime = startTime;
		this.presenter = presenter;
		this.presenterIntroduction = presenterIntroducation;
		this.isSilenceMode = isSilenceMode;
		this.introduction = introduction;
		this.isShowDetailPage = isShowDetailPage;
		this.passwd = passwd;
		this.ticketsMoney = ticketsMoney;
		this.isDistribution = isDistribution;
		this.distributionPercent = distributionPercent;
		this.liveType = liveType;
		this.courseForm = courseForm;
		this.seriesCourse = seriesCourse;
		this.specialOffer = specialOffer;
		this.isEnd = isEnd;
		this.buyCount = buyCount;
	}

}
