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
public class LiveStudioPageSettings extends DomainObject {

	private String img;
	private String link;
	private String title;
	private LinkType linkType;
	private LiveStudio liveStudio;

	@Getter
	public enum LinkType implements BaseEnum<LinkType, String> {
		COURSE("1", "课程"), VIP("2", "VIP页面"), LINK("3", "普通链接"), NONE("4", "无");

		private String code;
		private String name;

		private LinkType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
