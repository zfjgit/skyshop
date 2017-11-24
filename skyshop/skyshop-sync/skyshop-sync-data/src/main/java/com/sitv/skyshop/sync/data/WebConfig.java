/**
 * 
 */
package com.sitv.skyshop.sync.data;

import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sitv.skyshop.sync.data.canal.ClusterCanalClient;
import com.sitv.skyshop.sync.data.canal.SimpleCanalClient;

/**
 * 
 * @author zfj20 @ 2017年9月20日
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(WebConfig.class);
	
	@Bean
	public ClusterCanalClient clusterClient() {
		log.info("clusterClient!!!!");
		return new ClusterCanalClient();
	}
	
	@Bean
	public ServletListenerRegistrationBean<ServletContextListener> clusterServletListenerRegistrationBean() {
		ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
		bean.setListener(clusterClient());
		return bean;
	}
	
	@Bean
	public SimpleCanalClient simpleClient() {
		log.info("simpleClient!!!!");
		return new SimpleCanalClient();
	}
	
	@Bean
	public ServletListenerRegistrationBean<ServletContextListener> simpleServletListenerRegistrationBean() {
		ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
		bean.setListener(simpleClient());
		return bean;
	}
}
