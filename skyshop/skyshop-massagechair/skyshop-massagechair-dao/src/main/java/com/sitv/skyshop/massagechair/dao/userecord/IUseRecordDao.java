/**
 *
 */
package com.sitv.skyshop.massagechair.dao.userecord;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.record.UseRecord;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IUseRecordDao extends ICrudDao<UseRecord> {

	UseRecord getByOrder(@Param("orderId") Long orderId, @Param("type") String type, @Param("resultCode") String resultCode);

	UseRecord getLast(@Param("sim") String sim, @Param("type") String type, @Param("orderId") String orderId);
}
