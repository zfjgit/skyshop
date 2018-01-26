/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.course;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.wisdomedu.dto.course.SeriesCourseCategoryInfo;

/**
 * @author zfj20
 */
public interface ISeriesCourseCategoryService extends IBaseService<SeriesCourseCategoryInfo> {

	void sort(String[] ids);

}
