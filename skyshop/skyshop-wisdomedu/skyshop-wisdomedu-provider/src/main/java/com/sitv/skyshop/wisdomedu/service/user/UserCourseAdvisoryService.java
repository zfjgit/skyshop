/**
 *
 */
package com.sitv.skyshop.wisdomedu.service.user;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.wisdomedu.dao.user.IUserCourseAdvisoryDao;
import com.sitv.skyshop.wisdomedu.domain.user.UserCourseAdvisory;
import com.sitv.skyshop.wisdomedu.dto.user.UserCourseAdvisoryInfo;

/**
 * @author zfj20
 */
@Service
public class UserCourseAdvisoryService extends CrudService<IUserCourseAdvisoryDao, UserCourseAdvisory, UserCourseAdvisoryInfo> implements IUserCourseAdvisoryService {

	public UserCourseAdvisoryInfo getOne(Long id) {
		return UserCourseAdvisoryInfo.create(get(id));
	}

	public PageInfo<UserCourseAdvisoryInfo> search(SearchParamInfo<UserCourseAdvisoryInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<UserCourseAdvisoryInfo> infos = UserCourseAdvisoryInfo.creates(dao.search(q));

		com.github.pagehelper.PageInfo<UserCourseAdvisoryInfo> pageInfo = new com.github.pagehelper.PageInfo<>(infos, 5);

		return new PageInfo<>(pageInfo.getList(), q.getPage(), q.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(UserCourseAdvisoryInfo t) {
	}

	public void createOne(UserCourseAdvisoryInfo t) {
	}

	public void reply(UserCourseAdvisoryInfo info) {
		UserCourseAdvisory advisory = get(info.getId());
		advisory.setReply(info.getReply());
		advisory.setReplyTime(Calendar.getInstance());

		dao.reply(advisory);
	}

}
