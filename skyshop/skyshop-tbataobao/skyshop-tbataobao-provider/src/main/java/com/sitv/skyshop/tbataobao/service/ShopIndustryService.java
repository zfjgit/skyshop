/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.tbataobao.dao.IShopIndustryDao;
import com.sitv.skyshop.tbataobao.domain.ShopIndustry;
import com.sitv.skyshop.tbataobao.dto.ShopIndustryInfo;

/**
 * @author zfj20
 */
@Service
public class ShopIndustryService extends CrudService<IShopIndustryDao, ShopIndustry, ShopIndustryInfo> implements IShopIndustryService {

	public ShopIndustryInfo getOne(Long id) {
		return null;
	}

	public PageInfo<ShopIndustryInfo> search(SearchParamInfo<ShopIndustryInfo> q) {
		return null;
	}

	public void updateOne(ShopIndustryInfo t) {
	}

	public void createOne(ShopIndustryInfo t) {
	}

	public List<ShopIndustryInfo> findAll() {
		List<ShopIndustryInfo> list = ShopIndustryInfo.creates(dao.findAll());
		list.add(0, new ShopIndustryInfo(0l, "请选择"));
		return list;
	}
}
