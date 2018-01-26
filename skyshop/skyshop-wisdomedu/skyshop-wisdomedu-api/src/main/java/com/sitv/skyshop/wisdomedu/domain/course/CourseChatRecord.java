/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.course;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Setter
@Getter
public class CourseChatRecord extends DomainObject {
	private SingleCourse course;
	private CourseChatRecordType courseChatRecordType;
	private String link;
	private Integer seconds;
	private Integer size;
	private String content;

	@Getter
	public enum CourseChatRecordType implements BaseEnum<CourseChatRecordType, String> {
		AUDIO("1", ""), IMG("2", ""), VIDEO("3", ""), PPT("4", ""), TEXT("5", "");

		private String code;
		private String name;

		private CourseChatRecordType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
