/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.user;

import java.util.Calendar;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class UserCourseAdvisory extends DomainObject {

	private AdvisorySubjectType courseType;
	private DomainObject course;
	private User user;
	private String content;
	private int praiseCount;
	private String reply;
	private Calendar replyTime;

	@Getter
	public enum AdvisorySubjectType implements BaseEnum<AdvisorySubjectType, String> {
		COURSE("1", ""), SERIESCOURSE("2", "");

		private AdvisorySubjectType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		private String code;
		private String name;
	}
}
