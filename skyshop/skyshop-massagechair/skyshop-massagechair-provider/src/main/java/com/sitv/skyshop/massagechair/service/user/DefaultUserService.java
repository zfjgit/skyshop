/**
 *
 */
package com.sitv.skyshop.massagechair.service.user;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.domain.user.User;
import com.sitv.skyshop.massagechair.dto.user.UserInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月23日
 */
public abstract class DefaultUserService<D extends ICrudDao<T>, I extends UserInfo, T extends User> extends CrudService<D, T, I> implements IUserService<I> {

	public I getOne(Long id) {
		return UserInfo.create(get(id));
	}

	public PageInfo<I> search(SearchParamInfo<I> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<T> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<T> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<I> infos = UserInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(I t) {
		T u = get(t.getId());
		u.setEmail(t.getEmail());
		u.setMobile(t.getMobile());
		u.setName(t.getName());
		u.setDescription(t.getDescription());
		update(u);
	}

	public void createOne(I t) {
		throw new UnsupportedOperationException("不能创建");
	}

}
