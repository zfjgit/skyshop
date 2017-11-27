/**
 * 
 */
package com.sitv.skyshop.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author zfj20
 * @version 2017年7月28日
 */
@Configuration
@EnableCaching
public class EhcacheConfig {

    @Bean("ehCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(@Qualifier("ehCacheManagerFactoryBean") EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager(bean.getObject());
    }


    @Bean("ehCacheManagerFactoryBean")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();

        factoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
        factoryBean.setShared(true);

        return factoryBean;
    }

}
