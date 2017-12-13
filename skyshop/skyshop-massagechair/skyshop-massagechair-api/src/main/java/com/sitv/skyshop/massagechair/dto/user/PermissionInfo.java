/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.user.Permission;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月20日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PermissionInfo extends FullInfoDto {

	private static final long serialVersionUID = 8170687221231810662L;

	private String uri;

	public PermissionInfo() {
	}

	public PermissionInfo(Long id, String code, String name, String uri, Calendar createTime, Calendar updateTime) {
		super(id, code, name, null, createTime, updateTime);
		this.uri = uri;
	}

	public static PermissionInfo create(Permission p) {
		if (p == null) {
			return null;
		}
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
