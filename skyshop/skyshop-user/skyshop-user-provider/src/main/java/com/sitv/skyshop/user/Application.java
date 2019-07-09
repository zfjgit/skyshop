/**
 *
 */
package com.sitv.skyshop.user;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

/**
 * @author zfj20
 * @version 2017年7月25日
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.sitv.skyshop")
@ServletComponentScan
@SpringCloudApplication
public class Application {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setBannerMode(Mode.OFF);
		springApplication.setWebApplicationType(WebApplicationType.SERVLET);
		springApplication.run(args);
	}
}
