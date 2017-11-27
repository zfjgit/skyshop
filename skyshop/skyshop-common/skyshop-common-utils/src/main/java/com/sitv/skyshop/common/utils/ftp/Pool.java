/**
 * 
 */
package com.sitv.skyshop.common.utils.ftp;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 
 * @author zfj20 @ 2017年9月28日
 */
public abstract class Pool<T> {

	private final GenericObjectPool<T> genericObjectPool;
	
	public Pool(GenericObjectPoolConfig config, PooledObjectFactory<T> factory) {
		genericObjectPool = new GenericObjectPool<>(factory, config);
	}
	
	public T getObject() {
		try {
			return this.genericObjectPool.borrowObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void returnObject(T t) {
		this.genericObjectPool.returnObject(t);
	}
	
	public void close() {
		this.genericObjectPool.close();
	}
	
	public void clear() {
		this.genericObjectPool.close();
	}
}
