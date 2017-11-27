/**
 *
 */
package com.sitv.skyshop.massagechair;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.dao.datasource.DynamicDataSource;

/**
 * @author zfj20
 * @version 2017年8月8日
 */
@Configuration
@EnableTransactionManagement
@PropertySource(ignoreResourceNotFound = false, value = "classpath:mybatis-config.properties")
public class MybatisConfig {

	@Bean("dataSource")
	public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
		DynamicDataSource dataSource = new DynamicDataSource();

		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("read", slaveDataSource);
		targetDataSources.put("write", masterDataSource);

		dataSource.setTargetDataSources(targetDataSources);

		dataSource.setDefaultTargetDataSource(masterDataSource);

		return dataSource;
	}

	@Bean("sessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DynamicDataSource dataSource, @Autowired Environment env) throws Exception {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		bean.setTypeHandlersPackage(env.getProperty("mybatis.typeHandlersPackage"));
		bean.setMapperLocations(resourcePatternResolver.getResources(env.getProperty("mybatis.mapperLocations")));

		return bean.getObject();
	}

	@Bean("transactionManager")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource") DynamicDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean("mapperScannerConfigurer")
	public MapperScannerConfigurer mapperScannerConfigurer(@Autowired Environment env) {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage(env.getProperty("mybatis.mapperBasePackages"));
		configurer.setAnnotationClass(MyBatisDao.class);
		configurer.setSqlSessionFactoryBeanName("sessionFactory");
		configurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		return configurer;
	}
}
