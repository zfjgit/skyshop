/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.livestudio;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioWithrawRecord;

/**
 * @author zfj20
 */
@MyBatisDao
public interface ILiveStudioWithrawRecordDao extends ICrudDao<LiveStudioWithrawRecord> {

	void updateStatus(LiveStudioWithrawRecord info);

}
