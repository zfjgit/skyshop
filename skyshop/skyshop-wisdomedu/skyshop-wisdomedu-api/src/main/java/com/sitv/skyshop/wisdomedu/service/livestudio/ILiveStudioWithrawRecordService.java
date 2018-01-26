/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioWithrawRecordInfo;

/**
 * @author zfj20
 */
public interface ILiveStudioWithrawRecordService extends IBaseService<LiveStudioWithrawRecordInfo> {

	void updateStatus(LiveStudioWithrawRecordInfo info);
}
