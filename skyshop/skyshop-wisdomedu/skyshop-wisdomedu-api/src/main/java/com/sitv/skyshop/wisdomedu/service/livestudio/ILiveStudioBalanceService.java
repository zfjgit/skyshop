/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioBalanceInfo;

/**
 * @author zfj20
 */
public interface ILiveStudioBalanceService extends IBaseService<LiveStudioBalanceInfo> {

	LiveStudioBalanceInfo getBy(Long liveStudioId);
}
