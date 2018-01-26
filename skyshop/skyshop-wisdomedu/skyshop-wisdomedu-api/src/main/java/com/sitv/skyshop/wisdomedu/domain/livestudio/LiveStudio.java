/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.wisdomedu.domain.user.User;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudio extends DomainObject {

	private String introduction;
	private String headimg;
	private String backgroundimg;
	private String link;
	private User creator;
	private User admin;
	private LiveStudioType type;

}
