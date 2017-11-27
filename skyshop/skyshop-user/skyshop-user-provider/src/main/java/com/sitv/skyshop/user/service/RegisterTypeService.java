/**
 *
 */
package com.sitv.skyshop.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.user.dao.IRegisterTypeDao;
import com.sitv.skyshop.user.domain.RegisterType;
import com.sitv.skyshop.user.dto.RegisterTypeInfo;

/**
 * @author zfj20
 * @version 2017年8月7日
 */
@Service("registerTypeService")
public class RegisterTypeService extends CrudService<IRegisterTypeDao, RegisterType, RegisterTypeInfo> implements IRegisterTypeService {

	public RegisterTypeInfo getOne(Long id) {
		return RegisterTypeInfo.create(get(id));
	}

	public PageInfo<RegisterTypeInfo> search(SearchParamInfo<RegisterTypeInfo> q) {
		PageHelper.startPage(q.getPage(), q.getPageSize(), true);
		List<RegisterType> registerTypes = dao.search(q);
		com.github.pagehelper.PageInfo<RegisterType> pageInfo = new com.github.pagehelper.PageInfo<>(registerTypes, 5);

		List<RegisterTypeInfo> registerTypeInfos = RegisterTypeInfo.creates(pageInfo.getList());

		return new PageInfo<>(registerTypeInfos, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
	}

	public void deleteOne(Long id) {
		delete(id);
	}

	public void updateOne(RegisterTypeInfo t) {
		RegisterType registerType = get(t.getId());

		registerType.setCode(t.getCode());
		registerType.setName(t.getName());

		update(registerType);
	}

	public void createOne(RegisterTypeInfo t) {
		RegisterType registerType = new RegisterType(t.getName(), t.getCode());
		create(registerType);
	}
}
