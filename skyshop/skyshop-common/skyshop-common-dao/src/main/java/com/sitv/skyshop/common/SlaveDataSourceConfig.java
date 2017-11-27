/**
 * 
 */
package com.sitv.skyshop.common;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author zfj20
 * @version 2017年7月27日
 */
@Configuration
@PropertySource(ignoreResourceNotFound = false, value = "classpath:/config/slave-${spring.profiles.active}.properties")
public class SlaveDataSourceConfig {

	@Autowired
	private Environment env;

	@Bean("slaveDataSource")
	public DataSource slaveDataSource() throws SQLException {
		PropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "datasource.slave.");
		
		if (StringUtils.isEmpty(propertyResolver.getProperty("url"))) {
			throw new RuntimeException("Database connection pool is not configured correctly");
		}
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
		druidDataSource.setUrl(propertyResolver.getProperty("url"));
		druidDataSource.setUsername(propertyResolver.getProperty("username"));
		druidDataSource.setPassword(propertyResolver.getProperty("password"));
		druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));
		druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle")));
		druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive")));
		druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("maxWait")));
		druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
		druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
		druidDataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
		druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("testWhileIdle")));
		druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow")));
		druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("testOnReturn")));
		druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements")));
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));
		druidDataSource.setFilters(propertyResolver.getProperty("filters"));
		return druidDataSource;
	}
}
