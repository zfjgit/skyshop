/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.common.exception.EntityNotFoundException;
import com.sitv.skyshop.common.exception.EntityStatusException;
import com.sitv.skyshop.common.utils.Utils;
import com.sitv.skyshop.massagechair.dao.user.IUserDao;
import com.sitv.skyshop.massagechair.domain.user.User;
import com.sitv.skyshop.massagechair.domain.user.User.UserStatus;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;

/**
 * @author zfj20 @ 2017年11月24日
 */
@Service
public class UserService extends DefaultUserService<IUserDao<User>, UserInfo, User> implements IUserService<UserInfo> {

	public void updateOne(UserInfo t) {
		throw new UnsupportedOperationException("不能修改");
	}

	public UserInfo login(String code, String pwd) {
		User user = dao.getBy(code, Utils.digest(code + pwd, "MD5"));
		if (user == null) {
			throw new EntityNotFoundException("账号或密码错误");
		}
		if (user.getStatus() != UserStatus.NORMAL) {
			throw new EntityStatusException("账号状态不正常");
		}

		return UserInfo.create(user);
	}
}
