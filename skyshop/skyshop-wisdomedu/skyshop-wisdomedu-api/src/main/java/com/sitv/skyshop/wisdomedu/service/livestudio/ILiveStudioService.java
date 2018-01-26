/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;

/**
 * @author zfj20
 */
public interface ILiveStudioService extends IBaseService<LiveStudioInfo> {

	int getFollowCount(Long id);

	LiveStudioInfo getByOpenId(String openId);

	BigDecimal getIncome(Long id, Calendar startDate, Calendar endDate);

	void updateHeadimg(Long id, String headimg);

	void updateName(Long id, String name);

	void updateIntroduction(Long id, String introduction);

	void updateBackgroundimg(Long id, String bg);

}
