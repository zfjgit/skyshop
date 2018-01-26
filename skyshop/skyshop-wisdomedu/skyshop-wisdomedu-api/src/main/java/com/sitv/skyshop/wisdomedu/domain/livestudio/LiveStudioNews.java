/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioNews extends DomainObject {

	private LiveStudio liveStudio;
	private String content;
	private String link;
	private Integer praiseCount;

	private SubjectType subjectType;
	private DomainObject subject;

	@Getter
	public enum SubjectType implements BaseEnum<SubjectType, String> {
		TOPIC("1", ""), SERIESCOURSE("2", ""), PUNCH("3", ""), VIP("4", ""), ASK("5", "");

		private String code;
		private String name;

		private SubjectType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
