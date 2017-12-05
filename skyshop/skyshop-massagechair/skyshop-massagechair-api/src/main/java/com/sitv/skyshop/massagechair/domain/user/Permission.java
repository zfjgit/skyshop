/**
 *
 */
package com.sitv.skyshop.massagechair.domain.user;

import com.sitv.skyshop.domain.DomainObject;

/**
 * @author zfj20 @ 2017年11月20日
 */
public class Permission extends DomainObject {

	private String uri;

	protected Permission() {
	}

	/**
	 * @param code
	 * @param name
	 * @param uri
	 */
	public Permission(String code, String name, String uri) {
		super(name, code);
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
