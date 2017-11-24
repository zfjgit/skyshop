/**
 *
 */
package com.sitv.skyshop.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sitv.skyshop.common.dao.IConfigurationDao;
import com.sitv.skyshop.common.domain.Configuration;
import com.sitv.skyshop.common.dto.ConfigurationInfo;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;
import com.sitv.skyshop.service.CrudService;
import com.sitv.skyshop.service.IBaseService;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
@Service("configurationService")
public class ConfigurationService extends CrudService<IConfigurationDao, Configuration, ConfigurationInfo> implements IBaseService<ConfigurationInfo> {

	public ConfigurationInfo getOne(Long id) {
		return ConfigurationInfo.create(super.dao.get(id));
	}

	public PageInfo<ConfigurationInfo> search(SearchParamInfo<ConfigurationInfo> q) {
		int page = q.getPage();
		int pageSize = q.getPageSize();

		PageHelper.startPage(page, pageSize);
		List<Configuration> configurations = super.dao.search(q);
		com.github.pagehelper.PageInfo<Configuration> pageInfo1 = new com.github.pagehelper.PageInfo<>(configurations, 5);

		List<ConfigurationInfo> configurationInfos = ConfigurationInfo.creates(pageInfo1.getList());

		return new PageInfo<>(configurationInfos, pageInfo1.getPageNum(), pageInfo1.getPageSize(), pageInfo1.getPages(), pageInfo1.getTotal());
	}

	public void deleteOne(Long id) {
		delete(id);
	}

	public void updateOne(ConfigurationInfo configInfo) {
		Configuration configuration = get(configInfo.getId());

		if (configInfo.getParentId() == 0l) {
			configuration.setParent(configuration.getRoot());
		} else {
			configuration.setParent(get(configInfo.getParentId()));
		}

		configuration.setClassification(configInfo.getClassification());
		configuration.setCode(configInfo.getCode());
		configuration.setImgurl(configInfo.getImgurl());
		configuration.setLevel(configInfo.getLevel());
		configuration.setName(configInfo.getName());
		configuration.setSerialNumber(configInfo.getSerialNumber());

		update(configuration);
	}

	public void createOne(ConfigurationInfo configInfo) {
		Configuration configuration = new Configuration(configInfo.getName(), configInfo.getClassification(), configInfo.getLevel(), configInfo.getSerialNumber());

		if (configInfo.getParentId() == 0l) {
			configuration.setParent(configuration.getRoot());
		} else {
			configuration.setParent(get(configInfo.getParentId()));
		}

		configuration.setCode(configInfo.getCode());
		configuration.setImgurl(configInfo.getImgurl());

		create(configuration);
	}

}
