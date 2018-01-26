/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.course;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourseCategory;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Setter
@Getter
public class SeriesCourseCategoryInfo extends FullInfoDto {
	private static final long serialVersionUID = -5029367505103730178L;

	private LiveStudioInfo liveStudio;
	private int order;

	public SeriesCourseCategoryInfo() {
	}

	public SeriesCourseCategoryInfo(Long id, String name, LiveStudioInfo liveStudio, int order) {
		super(id, name);
		this.liveStudio = liveStudio;
		this.order = order;
	}

	public static SeriesCourseCategoryInfo create(SeriesCourseCategory c) {
		if (c == null) {
			return null;
		}
		return new SeriesCourseCategoryInfo(c.getId(), c.getName(), LiveStudioInfo.create(c.getLiveStudio()), c.getOrder());
	}

	public static List<SeriesCourseCategoryInfo> creates(List<SeriesCourseCategory> list) {
		List<SeriesCourseCategoryInfo> infos = new ArrayList<>();
		if (list == null) {
			return infos;
		}
		for (SeriesCourseCategory u : list) {
			if (u == null) {
				continue;
			}
			infos.add(SeriesCourseCategoryInfo.create(u));
		}
		return infos;
	}
}
