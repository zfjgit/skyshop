/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.livestudio;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;

/**
 * @author zfj20
 */
@MyBatisDao
public interface ILiveStudioDao extends ICrudDao<LiveStudio> {

	int getFollowCount(@Param("id") Long id);

	LiveStudio getByOpenId(@Param("openId") String openId);

	BigDecimal getIncome(@Param("id") Long id, @Param("startTime") Calendar startTime, @Param("endTime") Calendar endTime);

	void updateHeadimg(@Param("id") Long id, @Param("headimg") String headimg);

	void updateName(@Param("id") Long id, @Param("name") String name);

	void updateIntroduction(@Param("id") Long id, @Param("introduction") String introduction);

	void updateBackgroundimg(@Param("id") Long id, @Param("bg") String bg);
}
