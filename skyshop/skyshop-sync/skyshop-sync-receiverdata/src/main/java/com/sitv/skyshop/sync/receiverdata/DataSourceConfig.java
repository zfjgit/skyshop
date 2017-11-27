package com.sitv.skyshop.sync.receiverdata;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author zfj20
 * @version 2017年7月27日
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	private Environment env;

	@Primary
	@Bean("mysqlDataSource")
	public DataSource mysqlDataSource() throws SQLException {
		PropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "datasource.mysql.");

		if (StringUtils.isEmpty(propertyResolver.getProperty("url"))) {
			throw new RuntimeException("Database connection pool is not configured correctly");
		}

		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
		druidDataSource.setUrl(propertyResolver.getProperty("url"));
		druidDataSource.setUsername(propertyResolver.getProperty("username"));
		druidDataSource.setPassword(propertyResolver.getProperty("password"));

		druidDataSource.setInitialSize(Integer.parseInt(env.getProperty("datasource.initialSize")));
		druidDataSource.setMinIdle(Integer.parseInt(env.getProperty("datasource.minIdle")));
		druidDataSource.setMaxActive(Integer.parseInt(env.getProperty("datasource.maxActive")));
		druidDataSource.setMaxWait(Integer.parseInt(env.getProperty("datasource.maxWait")));
		druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("datasource.timeBetweenEvictionRunsMillis")));
		druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("datasource.minEvictableIdleTimeMillis")));
		druidDataSource.setValidationQuery(env.getProperty("datasource.validationQuery"));
		druidDataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("datasource.testWhileIdle")));
		druidDataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("datasource.testOnBorrow")));
		druidDataSource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("datasource.testOnReturn")));
		druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(env.getProperty("datasource.poolPreparedStatements")));
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("datasource.maxPoolPreparedStatementPerConnectionSize")));
		druidDataSource.setFilters(env.getProperty("datasource.filters"));
		return druidDataSource;
	}

	// @Bean("sqlserverDataSource")
	public DataSource sqlserverDataSource() throws SQLException {
		PropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "datasource.sqlserver.");

		if (StringUtils.isEmpty(propertyResolver.getProperty("url"))) {
			throw new RuntimeException("Database connection pool is not configured correctly");
		}

		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
		druidDataSource.setUrl(propertyResolver.getProperty("url"));
		druidDataSource.setUsername(propertyResolver.getProperty("username"));
		druidDataSource.setPassword(propertyResolver.getProperty("password"));

		druidDataSource.setInitialSize(Integer.parseInt(env.getProperty("datasource.initialSize")));
		druidDataSource.setMinIdle(Integer.parseInt(env.getProperty("datasource.minIdle")));
		druidDataSource.setMaxActive(Integer.parseInt(env.getProperty("datasource.maxActive")));
		druidDataSource.setMaxWait(Integer.parseInt(env.getProperty("datasource.maxWait")));
		druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("datasource.timeBetweenEvictionRunsMillis")));
		druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("datasource.minEvictableIdleTimeMillis")));
		druidDataSource.setValidationQuery(env.getProperty("datasource.validationQuery"));
		druidDataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("datasource.testWhileIdle")));
		druidDataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("datasource.testOnBorrow")));
		druidDataSource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("datasource.testOnReturn")));
		druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(env.getProperty("datasource.poolPreparedStatements")));
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("datasource.maxPoolPreparedStatementPerConnectionSize")));
		druidDataSource.setFilters(env.getProperty("datasource.filters"));
		return druidDataSource;
	}
}
