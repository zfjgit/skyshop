/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.user;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.user.IUserDao;
import com.sitv.skyshop.wisdomedu.domain.user.User;
import com.sitv.skyshop.wisdomedu.dto.user.UserInfo;
import com.sitv.skyshop.wisdomedu.service.user.IUserService;

/**
 * @author zfj20
 */
@Service
public class UserService extends CrudService<IUserDao, User, UserInfo> implements IUserService {

	public UserInfo getOne(Long id) {
		return null;
	}

	public PageInfo<UserInfo> search(SearchParamInfo<UserInfo> q) {
		return null;
	}

	public void updateOne(UserInfo t) {
	}

	public void createOne(UserInfo t) {
	}

}
