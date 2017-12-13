/**
 *
 */
package com.sitv.skyshop.massagechair.dao.user;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.user.User;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IUserDao<T extends User> extends ICrudDao<T>, IDeleteStatusDao<User> {
	User getBy(@Param("code") String code, @Param("pwd") String pwd);

	void updateStatus(User user);

	void updateLoginCheckCode(@Param("id") Long id, @Param("loginCheckCode") String loginCheckCode);
}
