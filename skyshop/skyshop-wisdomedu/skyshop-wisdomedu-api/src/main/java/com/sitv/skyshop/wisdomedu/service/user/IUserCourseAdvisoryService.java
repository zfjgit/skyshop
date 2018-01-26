/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.user;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.wisdomedu.dto.user.UserCourseAdvisoryInfo;

/**
 * @author zfj20
 */
public interface IUserCourseAdvisoryService extends IBaseService<UserCourseAdvisoryInfo> {

	@Transactional(propagation = Propagation.REQUIRED)
	void reply(UserCourseAdvisoryInfo info);
}
