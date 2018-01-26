/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.user.IUserLiveStudioDao;
import com.sitv.skyshop.wisdomedu.domain.user.UserLiveStudio;
import com.sitv.skyshop.wisdomedu.dto.user.UserLiveStudioInfo;

/**
 * @author zfj20
 */
@Service
public class UserLiveStudioService extends CrudService<IUserLiveStudioDao, UserLiveStudio, UserLiveStudioInfo> implements IUserLiveStudioService {

	public UserLiveStudioInfo getOne(Long id) {
		return null;
	}

	public PageInfo<UserLiveStudioInfo> search(SearchParamInfo<UserLiveStudioInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<UserLiveStudioInfo> infos = UserLiveStudioInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<UserLiveStudioInfo> pageInfo = new com.github.pagehelper.PageInfo<>(infos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(UserLiveStudioInfo t) {
	}

	public void createOne(UserLiveStudioInfo t) {
	}

}
