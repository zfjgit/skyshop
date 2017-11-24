package com.sitv.skyshop.dao.mapperscan;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Import({ MapperScannerRegistrar.class })
public @interface MapperScan {

	public abstract String[] value() default {};

	public abstract String[] basePackages() default {};

	public abstract Class<?>[] basePackageClasses() default {};

	public abstract Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

	public abstract Class<? extends Annotation> annotationClass() default Annotation.class;

	public abstract Class<?> markerInterface() default Class.class;

	public abstract String sqlSessionTemplateRef() default "";

	public abstract String sqlSessionFactoryRef() default "";

	@SuppressWarnings("rawtypes")
	public abstract Class<? extends MapperFactoryBean> factoryBean() default MapperFactoryBean.class;
}
