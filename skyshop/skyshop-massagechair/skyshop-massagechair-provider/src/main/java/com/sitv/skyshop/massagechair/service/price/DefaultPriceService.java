/**
 *
 */
package com.sitv.skyshop.massagechair.service.price;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.massagechair.domain.price.Price;
import com.sitv.skyshop.massagechair.dto.price.PriceInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20 @ 2017年11月20日
 */
public abstract class DefaultPriceService<D extends ICrudDao<T>, T extends Price, I extends PriceInfo> extends CrudService<D, T, I> implements IPriceService<I> {

	public I getOne(Long id) {
		return PriceInfo.create(get(id));
	}

	public PageInfo<I> search(SearchParamInfo<I> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);

		List<T> entitys = dao.search(q);

		com.github.pagehelper.PageInfo<T> pageInfo = new com.github.pagehelper.PageInfo<>(entitys, 5);

		List<I> infos = PriceInfo.creates(pageInfo.getList());

		return new PageInfo<>(infos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void updateOne(I t) {
		T price = get(t.getId());
		price.setPrice(t.getPrice());
		price.setName(t.getName());
		update(price);
	}

	public void createOne(I t) {
		throw new UnsupportedOperationException("不能创建");
	}

}
