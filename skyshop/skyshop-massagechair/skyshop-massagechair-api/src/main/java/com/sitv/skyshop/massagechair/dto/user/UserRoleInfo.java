/**
 *
 */
package com.sitv.skyshop.massagechair.dto.user;

import com.sitv.skyshop.dto.info.SimpleInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20 @ 2017年11月15日
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserRoleInfo extends SimpleInfoDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 4444853286189390068L;

	private RoleInfo role;
	private ManagerInfo manager;

}
