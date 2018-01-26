/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.course;

import com.sitv.skyshop.domain.SimpleType;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Setter
@Getter
public class SeriesCourseCategory extends SimpleType {

	private LiveStudio liveStudio;
	private int order;

}
