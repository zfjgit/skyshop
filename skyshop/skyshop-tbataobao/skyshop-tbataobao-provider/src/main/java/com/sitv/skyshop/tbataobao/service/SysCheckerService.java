/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.tbataobao.dao.ISysCheckerDao;
import com.sitv.skyshop.tbataobao.domain.SysChecker;
import com.sitv.skyshop.tbataobao.dto.SysCheckerInfo;
import com.sitv.skyshop.tbataobao.service.ISysCheckerService;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Service
public class SysCheckerService extends CrudService<ISysCheckerDao, SysChecker, SysCheckerInfo> implements ISysCheckerService {

	public SysCheckerInfo getOne(Long id) {
		return null;
	}

	public PageInfo<SysCheckerInfo> search(SearchParamInfo<SysCheckerInfo> q) {
		return null;
	}

	public void updateOne(SysCheckerInfo t) {
	}

	public void createOne(SysCheckerInfo t) {
	}

	public SysCheckerInfo login(SysCheckerInfo i) {
		SysChecker sysChecker = dao.get(i.getAccount(), Utils.encrypt(i.getPwd()));

		if (sysChecker != null) {
			// String token = Utils.UUID();
			// redisTemplate.opsForValue().set(Constants.TOKEN_REDIS_KEY_PREFIX + i.getAccount(), System.currentTimeMillis() + "", 30l,
			// TimeUnit.MINUTES);
		}
		return SysCheckerInfo.create(sysChecker);
	}

	public void logout(String account) {
		// redisTemplate.delete(Constants.TOKEN_REDIS_KEY_PREFIX + account);
	}

}
