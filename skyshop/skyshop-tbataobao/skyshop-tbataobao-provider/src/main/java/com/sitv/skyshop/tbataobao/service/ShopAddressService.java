/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.tbataobao.dao.IShopAddressDao;
import com.sitv.skyshop.tbataobao.domain.ShopAddress;
import com.sitv.skyshop.tbataobao.dto.ShopAddressInfo;

/**
 * @author zfj20
 */
@Service
public class ShopAddressService extends CrudService<IShopAddressDao, ShopAddress, ShopAddressInfo> implements IShopAddressService {

	public ShopAddressInfo getOne(Long id) {
		return null;
	}

	public PageInfo<ShopAddressInfo> search(SearchParamInfo<ShopAddressInfo> q) {
		return null;
	}

	public void updateOne(ShopAddressInfo t) {
	}

	public void createOne(ShopAddressInfo t) {
	}

	public List<ShopAddressInfo> findChildrens(Long parentId) {
		List<ShopAddressInfo> list = ShopAddressInfo.creates(dao.findChildrens(parentId));
		list.add(0, new ShopAddressInfo(0l, "请选择", null));
		return list;
	}

}
