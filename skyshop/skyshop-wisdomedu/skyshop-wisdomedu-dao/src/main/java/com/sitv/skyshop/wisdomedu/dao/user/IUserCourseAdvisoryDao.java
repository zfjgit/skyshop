/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.user;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.wisdomedu.domain.user.UserCourseAdvisory;

/**
 * @author zfj20
 */
@MyBatisDao
public interface IUserCourseAdvisoryDao extends ICrudDao<UserCourseAdvisory> {

	void reply(UserCourseAdvisory advisory);
}
