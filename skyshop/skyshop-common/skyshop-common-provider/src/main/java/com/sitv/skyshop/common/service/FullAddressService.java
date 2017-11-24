/**
 *
 */
package com.sitv.skyshop.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.dao.IAddressDao;
import com.sitv.skyshop.common.dao.IFullAddressDao;
import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.common.domain.FullAddress;
import com.sitv.skyshop.common.dto.FullAddressInfo;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Service("fullAddressService")
public class FullAddressService extends CrudService<IFullAddressDao, FullAddress, FullAddressInfo> implements IBaseService<FullAddressInfo> {

	@Autowired
	private IAddressDao addressDao;

	public FullAddressInfo getOne(Long id) {
		return FullAddressInfo.create(get(id));
	}

	public PageInfo<FullAddressInfo> search(SearchParamInfo<FullAddressInfo> q) {
		int page = q.getPage();
		int pageSize = q.getPageSize();

		PageHelper.startPage(page, pageSize);
		List<FullAddress> addresses = super.dao.search(q);
		com.github.pagehelper.PageInfo<FullAddress> pageInfo1 = new com.github.pagehelper.PageInfo<>(addresses, 5);

		List<FullAddressInfo> addressInfos = FullAddressInfo.creates(pageInfo1.getList());

		return new PageInfo<>(addressInfos, pageInfo1.getPageNum(), pageInfo1.getPageSize(), pageInfo1.getPages(), pageInfo1.getTotal());
	}

	public void deleteOne(Long id) {
		delete(id);
	}

	public void updateOne(FullAddressInfo fullAddressInfo) {
		StringBuilder fullAddressText = new StringBuilder();

		FullAddress fullAddress = get(fullAddressInfo.getId());

		Address province = null;
		if (fullAddressInfo.getProvinceId() != null) {
			province = addressDao.get(fullAddressInfo.getProvinceId());
			fullAddressText.append(province.getName());
		}
		Address city = null;
		if (fullAddressInfo.getCityId() != null) {
			city = addressDao.get(fullAddressInfo.getCityId());
			fullAddressText.append(city.getName());
		}
		Address district = null;
		if (fullAddressInfo.getDistrictId() != null) {
			district = addressDao.get(fullAddressInfo.getDistrictId());
			fullAddressText.append(district.getName());
		}
		fullAddressText.append(fullAddressInfo.getDetailAddress());

		fullAddress.setProvince(province);
		fullAddress.setCity(city);
		fullAddress.setDistrict(district);
		fullAddress.setDetailAddress(fullAddressInfo.getDetailAddress());
		fullAddress.setFullAddress(fullAddressText.toString());

		update(fullAddress);
	}

	public void createOne(FullAddressInfo fullAddressInfo) {
		StringBuilder fullAddress = new StringBuilder();

		Address province = null;
		if (fullAddressInfo.getProvinceId() != null) {
			province = addressDao.get(fullAddressInfo.getProvinceId());
			fullAddress.append(province.getName());
		}
		Address city = null;
		if (fullAddressInfo.getCityId() != null) {
			city = addressDao.get(fullAddressInfo.getCityId());
			fullAddress.append(city.getName());
		}
		Address district = null;
		if (fullAddressInfo.getDistrictId() != null) {
			district = addressDao.get(fullAddressInfo.getDistrictId());
			fullAddress.append(district.getName());
		}

		fullAddress.append(fullAddressInfo.getDetailAddress());

		create(new FullAddress(province, city, district, fullAddressInfo.getDetailAddress(), fullAddress.toString()));
	}

}
