/**
 *
 */
package com.sitv.skyshop.wisdomedu.domain.livestudio;

import com.sitv.skyshop.domain.SimpleType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class LiveStudioType extends SimpleType {

	private LiveStudioType parent;
	private int order;
}
