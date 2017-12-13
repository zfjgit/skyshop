/**
 *
 */
package com.sitv.skyshop.common.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.sitv.skyshop.common.utils.ftp.FTPPool;
import com.sitv.skyshop.common.utils.ftp.FTPPooledObjectFactory;

/**
 * @author zfj20 @ 2017年9月28日
 */
// @Configuration
public class FTPConfig {

	@Autowired
	private Environment env;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public FTPPool init() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setTestOnReturn(true);
		FTPPooledObjectFactory factory = new FTPPooledObjectFactory(env.getProperty("ftp.host"), env.getProperty("ftp.port", Integer.class), env.getProperty("ftp.user"),
		                env.getProperty("ftp.pwd"), env.getProperty("ftp.dir"), env.getProperty("ftp.passiveModeConf"));

		FTPPool pool = new FTPPool(config, factory);

		return pool;
	}
}
