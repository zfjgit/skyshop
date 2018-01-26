/**
 *
 */
package com.sitv.skyshop.tbataobao;

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

import com.sitv.skyshop.tbataobao.dao.annotation.Mybatis_CheckRemark;

/**
 * @author zfj20
 * @version 2017年8月8日
 */
@Configuration
@EnableTransactionManagement
@PropertySource(ignoreResourceNotFound = false, value = "classpath:mybatis-config.properties")
public class MybatisConfig_CheckRemark {

	@Bean("sessionFactory_checkRemark")
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource_checkRemark") DataSource dataSource, @Autowired Environment env) throws Exception {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		bean.setTypeHandlersPackage(env.getProperty("mybatis.typeHandlersPackage"));
		bean.setMapperLocations(resourcePatternResolver.getResources(env.getProperty("mybatis.mapperLocations")));

		return bean.getObject();
	}

	@Bean("transactionManager_checkRemark")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource_checkRemark") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("sqlSessionTemplate_checkRemark")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sessionFactory_checkRemark") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean("mapperScannerConfigurer_checkRemark")
	public MapperScannerConfigurer mapperScannerConfigurer(@Autowired Environment env) {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage(env.getProperty("mybatis.mapperBasePackages"));
		configurer.setSqlSessionFactoryBeanName("sessionFactory_checkRemark");
		configurer.setSqlSessionTemplateBeanName("sqlSessionTemplate_checkRemark");
		configurer.setAnnotationClass(Mybatis_CheckRemark.class);
		return configurer;
	}
}
