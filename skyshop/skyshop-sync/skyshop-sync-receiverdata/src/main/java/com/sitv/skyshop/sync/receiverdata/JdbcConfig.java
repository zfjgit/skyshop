/**
 *
 */
package com.sitv.skyshop.sync.receiverdata;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author zfj20 @ 2017年9月15日
 */
@Configuration
public class JdbcConfig {

	@Primary
	@Bean(name = "mysqlJdbcTemplate")
	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	// @Bean(name = "sqlserverJdbcTemplate")
	public JdbcTemplate sqlserverJdbcTemplate(@Qualifier("sqlserverDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
