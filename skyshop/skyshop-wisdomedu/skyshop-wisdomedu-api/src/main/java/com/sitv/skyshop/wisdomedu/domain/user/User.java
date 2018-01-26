/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.user;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class User extends DomainObject {

	private String introduction;
	private String headimg;
	private String openid;
	private String payOpenid;
}
