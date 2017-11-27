/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.user.Permission;

/**
 * @author zfj20 @ 2017年11月20日
 */
public class PermissionInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 8170687221231810662L;

	private String uri;

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param uri
	 * @param createTime
	 * @param updateTime
	 */
	public PermissionInfo(Long id, String code, String name, String uri, Calendar createTime, Calendar updateTime) {
		super(id, code, name, null, createTime, updateTime);
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @param permission
	 * @return
	 */
	public static PermissionInfo create(Permission p) {
		return new PermissionInfo(p.getId(), p.getCode(), p.getName(), p.getUri(), p.getCreateTime(), p.getUpdateTime());
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<PermissionInfo> creates(List<Permission> list) {
		List<PermissionInfo> infos = new ArrayList<>();
		if (list != null) {
			for (Permission r : list) {
				infos.add(create(r));
			}
		}
		return infos;
	}
}
