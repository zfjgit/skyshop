/**
 *
 */
package com.sitv.skyshop.massagechair.dao.user;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.user.User;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MyBatisDao
public interface IUserDao<T extends User> extends ICrudDao<T> {

}
