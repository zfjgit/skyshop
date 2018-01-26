/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.livestudio;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.livestudio.ILiveStudioDao;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;
import com.sitv.skyshop.wisdomedu.dto.livestudio.LiveStudioInfo;

/**
 * @author zfj20
 */
@Service
public class LiveStudioService extends CrudService<ILiveStudioDao, LiveStudio, LiveStudioInfo> implements ILiveStudioService {

	public LiveStudioInfo getOne(Long id) {
		return LiveStudioInfo.create(get(id));
	}

	public PageInfo<LiveStudioInfo> search(SearchParamInfo<LiveStudioInfo> q) {
		return null;
	}

	public void updateOne(LiveStudioInfo t) {
	}

	public void createOne(LiveStudioInfo t) {
	}

	public LiveStudioInfo getByOpenId(String openId) {
		return LiveStudioInfo.create(dao.getByOpenId(openId));
	}

	public int getFollowCount(Long id) {
		return dao.getFollowCount(id);
	}

	public BigDecimal getIncome(Long id, Calendar startDate, Calendar endDate) {
		return dao.getIncome(id, startDate, endDate);
	}

	public void updateHeadimg(Long id, String headimg) {
		dao.updateHeadimg(id, headimg);
	}

	public void updateName(Long id, String name) {
		dao.updateName(id, name);
	}

	public void updateIntroduction(Long id, String introduction) {
		dao.updateIntroduction(id, introduction);
	}

	public void updateBackgroundimg(Long id, String bg) {
		dao.updateBackgroundimg(id, bg);
	}

}
