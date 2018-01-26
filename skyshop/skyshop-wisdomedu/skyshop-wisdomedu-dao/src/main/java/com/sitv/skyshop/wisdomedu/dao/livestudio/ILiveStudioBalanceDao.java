/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.livestudio;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioBalance;

/**
 * @author zfj20
 */
@MyBatisDao
public interface ILiveStudioBalanceDao extends ICrudDao<LiveStudioBalance> {

	LiveStudioBalance getBy(@Param("liveStudioId") Long liveStudioId);

}
