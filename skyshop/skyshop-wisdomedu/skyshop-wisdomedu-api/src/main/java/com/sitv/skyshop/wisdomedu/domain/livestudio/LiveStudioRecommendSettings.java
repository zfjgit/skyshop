/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import com.sitv.skyshop.domain.DomainObject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioRecommendSettings extends DomainObject {

	private LiveStudio liveStudio;
	private int order;
}
