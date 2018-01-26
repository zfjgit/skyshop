/**
 *
 */
package com.sitv.skyshop.wisdomedu;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sitv.skyshop.common.interceptor.auth.AuthorizationInterceptor;
import com.sitv.skyshop.common.utils.converters.CalendarConverter;
import com.sitv.skyshop.common.utils.resolvers.SearchParamResolver;

/**
 * @author zfj20 @ 2017年12月8日
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public AuthorizationInterceptor authorizationInterceptor() {
		return new AuthorizationInterceptor();
	}

	@Bean
	public SearchParamResolver searchParamResolver() {
		return new SearchParamResolver();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor())
		                // .addPathPatterns("/api/*")
		                .excludePathPatterns("/**/*.*", "/v2/**", "/swagger-resources/**");
		super.addInterceptors(registry);
	}

	public void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/**/*.*");
		super.addResourceHandlers(registry);
	}

	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new CalendarConverter());
		super.addFormatters(registry);
	}

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(searchParamResolver());
		super.addArgumentResolvers(argumentResolvers);
	}
}
