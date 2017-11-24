/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sitv.skyshop.dto.info.FullInfoDto;
import com.sitv.skyshop.massagechair.domain.user.Role;

/**
 * @author zfj20 @ 2017年11月15日
 */
public class RoleInfo extends FullInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 6902116025332992712L;

	private boolean isSuper;

	private List<PermissionInfo> permissions;

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param super
	 * @param createTime
	 * @param updateTime
	 */
	public RoleInfo(Long id, String code, String name, boolean issuper, Calendar createTime, Calendar updateTime) {
		super(id, code, name, createTime, updateTime);
		this.isSuper = issuper;
	}

	/**
	 * @return the isSuper
	 */
	public boolean isSuper() {
		return isSuper;
	}

	/**
	 * @param isSuper
	 *            the isSuper to set
	 */
	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	/**
	 * @param role
	 * @return
	 */
	public static RoleInfo create(Role role) {
		if (role == null) {
			return null;
		}
		return new RoleInfo(role.getId(), role.getCode(), role.getName(), role.isSuper(), role.getCreateTime(), role.getUpdateTime());
	}

	public static List<RoleInfo> creates(List<Role> list) {
		List<RoleInfo> infos = new ArrayList<>();
		if (list != null) {
			for (Role r : list) {
				infos.add(create(r));
			}
		}
		return infos;
	}

	public List<PermissionInfo> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionInfo> permissions) {
		this.permissions = permissions;
	}
}
