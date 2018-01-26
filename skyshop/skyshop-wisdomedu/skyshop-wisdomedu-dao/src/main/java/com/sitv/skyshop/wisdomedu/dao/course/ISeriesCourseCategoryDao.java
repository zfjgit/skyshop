/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourseCategory;

/**
 * @author zfj20
 */
@MyBatisDao
public interface ISeriesCourseCategoryDao extends ICrudDao<SeriesCourseCategory> {

	void updateOrder(@Param("orders") List<Map<String, Object>> orders);

}
