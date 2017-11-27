/**
 * 
 */
package com.sitv.skyshop.common.utils.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 
 * @author zfj20 @ 2017年9月28日
 */
public class FTPPool extends Pool<FTPClient> {

	public FTPPool(GenericObjectPoolConfig config, PooledObjectFactory<FTPClient> factory) {
		super(config, factory);
	}

}
