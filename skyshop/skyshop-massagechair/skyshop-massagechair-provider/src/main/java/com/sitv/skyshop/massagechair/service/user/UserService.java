/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.massagechair.dao.user.IUserDao;
import com.sitv.skyshop.massagechair.domain.user.User;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;

/**
 * @author zfj20 @ 2017年11月24日
 */
@Service
public class UserService extends DefaultUserService<IUserDao<User>, UserInfo, User> implements IUserService<UserInfo> {

	public void updateOne(UserInfo t) {
		throw new UnsupportedOperationException("不能修改");
	}
}
