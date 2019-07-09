/**
 *
 */
package com.sitv.skyshop.common;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zfj20
 * @version 2017年7月25日
 */
@RefreshScope
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@ComponentScan("com.sitv.skyshop")
@ServletComponentScan
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setBannerMode(Mode.OFF);
		springApplication.setWebApplicationType(WebApplicationType.SERVLET);
		springApplication.run(args);
	}
}
