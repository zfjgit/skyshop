/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import java.util.ArrayList;
import java.util.List;

import com.sitv.skyshop.dto.info.SimpleInfoDto;
import com.sitv.skyshop.massagechair.domain.user.RolePermission;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class RolePermissionInfo extends SimpleInfoDto {

	private static final long serialVersionUID = -4047200153348042173L;

	private RoleInfo role;
	private PermissionInfo permission;

	public RolePermissionInfo(Long id) {
		super(id);
	}

	public RolePermissionInfo() {
		this(null);
	}

	public RolePermissionInfo(RoleInfo role, PermissionInfo permission) {
		this();
		this.setPermission(permission);
		this.role = role;
	}

	/**
	 * @param rolePermission
	 * @return
	 */
	public static RolePermissionInfo create(RolePermission p) {
		if (p == null) {
			return null;
		}
		return new RolePermissionInfo(RoleInfo.create(p.getRole()), PermissionInfo.create(p.getPermission()));
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<RolePermissionInfo> creates(List<RolePermission> list) {
		List<RolePermissionInfo> infos = new ArrayList<>();
		if (list != null) {
			for (RolePermission rolePermission : list) {
				infos.add(create(rolePermission));
			}
		}
		return infos;
	}

}
