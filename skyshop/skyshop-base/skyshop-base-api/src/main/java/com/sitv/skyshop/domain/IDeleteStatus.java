/**
 *
 */
package com.sitv.skyshop.domain;

import com.sitv.skyshop.domain.DomainObject.DeleteStatus;

/**
 * @author zfj20 @ 2017年12月5日
 */
public interface IDeleteStatus {

	void setDeleteStatus(DeleteStatus deleteStatus);

	DeleteStatus getDeleteStatus();
}
