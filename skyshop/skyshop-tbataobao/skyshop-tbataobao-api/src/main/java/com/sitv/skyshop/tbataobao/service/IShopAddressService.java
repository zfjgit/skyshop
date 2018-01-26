/**
 *
 */
package com.sitv.skyshop.tbataobao.service;

import java.util.List;

import com.sitv.skyshop.service.IBaseService;
import com.sitv.skyshop.tbataobao.dto.ShopAddressInfo;

/**
 * @author zfj20
 */
public interface IShopAddressService extends IBaseService<ShopAddressInfo> {

	List<ShopAddressInfo> findChildrens(Long parentId);
}
