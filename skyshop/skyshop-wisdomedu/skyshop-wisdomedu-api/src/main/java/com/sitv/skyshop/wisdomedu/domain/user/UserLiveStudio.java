/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.user;

import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudio;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class UserLiveStudio extends DomainObject {

	private User user;
	private LiveStudio liveStudio;

	private boolean isLiveNotice;
}
