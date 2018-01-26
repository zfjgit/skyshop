/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.course;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Setter
@Getter
public class SingleCourse extends Course {

	private LiveStudio liveStudio;

	private String title;
	private String headimg;
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
	private int buyCount;

	private LiveType liveType;
	private CourseForm courseForm;
	private SeriesCourse seriesCourse;

	private JoinSeriesCourseStatus joinSeriesCourseStatus;

	private BigDecimal specialOffer;
	private boolean isEnd;

	@Getter
	public enum LiveType implements BaseEnum<LiveType, String> {
		FREE("1", "公开直播"), ENCRYPTED("2", "加密直播"), TICKETS("3", "收费直播");

		private String code;
		private String name;

		private LiveType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	@Getter
	public enum CourseForm implements BaseEnum<CourseForm, String> {
		AV_PLAYBACK("1", "音视频回放"), LECTURES("2", "讲座"), SLIDES("3", "幻灯片"), AV_INTERACTIVE("4", "音视频互动");

		private String code;
		private String name;

		private CourseForm(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	@Getter
	public enum JoinSeriesCourseStatus implements BaseEnum<JoinSeriesCourseStatus, String> {
		NOT_JOIN("1", "未加入"), JOINED("2", "已加入");

		private String code;
		private String name;

		private JoinSeriesCourseStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
