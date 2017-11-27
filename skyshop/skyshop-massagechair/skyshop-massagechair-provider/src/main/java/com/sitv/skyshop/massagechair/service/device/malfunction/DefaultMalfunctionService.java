/**
 *
 */
package com.sitv.skyshop.massagechair.service.device.malfunction;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionStatus;
import com.sitv.skyshop.massagechair.dto.device.malfunction.MalfunctionInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月23日
 */
public abstract class DefaultMalfunctionService<D extends ICrudDao<T>, T extends Malfunction, I extends MalfunctionInfo> extends CrudService<D, T, I>
                implements IMalfunctionService<I> {

	public I getOne(Long id) {
		return MalfunctionInfo.create(get(id));
	}

	public PageInfo<I> search(SearchParamInfo<I> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<T> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<T> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<I> infos = MalfunctionInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(I t) {
		T m = get(t.getId());
		m.setStatus(MalfunctionStatus.valueOf(t.getStatus()));
		update(m);
	}

	public void createOne(I t) {
		throw new UnsupportedOperationException("不能创建");
	}

}
