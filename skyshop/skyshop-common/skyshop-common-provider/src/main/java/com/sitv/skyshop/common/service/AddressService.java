/**
 *
 */
package com.sitv.skyshop.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.dao.IAddressDao;
import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.common.dto.AddressInfo;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;

/**
 * @author zfj20
 * @version 2017年7月27日
 */
@Service("addressService")
public class AddressService extends CrudService<IAddressDao, Address, AddressInfo> implements IAddressService {

	public AddressInfo getOne(Long id) {
		Address address = get(id);
		return AddressInfo.create(address);
	}

	public PageInfo<AddressInfo> search(SearchParamInfo<AddressInfo> q) {
		int page = q.getPage();
		int pageSize = q.getPageSize();

		PageHelper.startPage(page, pageSize);
		List<Address> addresses = super.dao.search(q);
		com.github.pagehelper.PageInfo<Address> pageInfo1 = new com.github.pagehelper.PageInfo<>(addresses, 5);

		List<AddressInfo> addressInfos = AddressInfo.creates(pageInfo1.getList());

		return new PageInfo<>(addressInfos, pageInfo1.getPageNum(), pageInfo1.getPageSize(), pageInfo1.getPages(), pageInfo1.getTotal());
	}

	public void updateOne(AddressInfo a) {
		Address address = get(a.getId());
		address.setName(a.getName());

		Address parent = null;
		if (a.getParentId() == 0) {
			parent = address.getRoot();
		} else {
			parent = get(a.getParentId());
		}
		address.setParent(parent);

		update(address);
	}

	public void createOne(AddressInfo a) {
		String name = a.getName();

		Address address = new Address(name);

		Address parent = null;
		if (a.getParentId() == 0) {
			parent = address.getRoot();
		} else {
			parent = get(a.getParentId());
		}
		address.setParent(parent);

		create(address);
	}

	public void deleteOne(Long id) {
		delete(id);
	}
}
