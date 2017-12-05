/**
 *
 */
package com.sitv.skyshop.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.common.domain.Configuration;
import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ConfigurationInfo extends FullInfoDto {

	private static final long serialVersionUID = -826378966088918864L;

	private int level;

	private String imgurl;

	private String classification;

	private ConfigurationInfo parent;

	private Long parentId;

	public ConfigurationInfo(String name, String code) {
		super(name, code);
	}

	public static List<ConfigurationInfo> creates(List<Configuration> list) {
		List<ConfigurationInfo> configurationInfos = new ArrayList<>();
		if (list == null) {
			return configurationInfos;
		}
		for (Configuration configuration : list) {
			configurationInfos.add(create(configuration));
		}
		return configurationInfos;
	}

	public static ConfigurationInfo create(Configuration configuration) {
		if (configuration == null) {
			return null;
		}
		ConfigurationInfo info = new ConfigurationInfo(configuration.getName(), configuration.getCode());
		info.setClassification(configuration.getClassification());
		info.setCreateTime(configuration.getCreateTime());
		info.setId(configuration.getId());
		info.setImgurl(configuration.getImgurl());
		info.setLevel(configuration.getLevel());

		if (configuration.getParent() == null) {
			info.setParent(null);
			info.setParentId(0l);
		} else {
			ConfigurationInfo parentInfo = create(configuration.getParent());
			info.setParent(parentInfo);
			info.setParentId(parentInfo.getId());
		}
		return info;
	}

}
