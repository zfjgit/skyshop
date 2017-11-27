package com.sitv.skyshop.dao.mapperscan;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class MapperScannerRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	private Environment environment;

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName()));
		ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);

		if (this.environment != null) {
			scanner.setEnvironment(environment);
		}

		Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
		if (!(Annotation.class.equals(annotationClass))) {
			scanner.setAnnotationClass(annotationClass);
		}

		Class<?> markerInterface = annoAttrs.getClass("markerInterface");
		if (!(Class.class.equals(markerInterface))) {
			scanner.setMarkerInterface(markerInterface);
		}

		Class<?> generatorClass = annoAttrs.getClass("nameGenerator");
		if (!(BeanNameGenerator.class.equals(generatorClass))) {
			scanner.setBeanNameGenerator((BeanNameGenerator) BeanUtils.instantiateClass(generatorClass));
		}

		Class<?> mapperFactoryBeanClass = annoAttrs.getClass("factoryBean");
		if (!(MapperFactoryBean.class.equals(mapperFactoryBeanClass))) {
			scanner.setMapperFactoryBean((MapperFactoryBean<?>) BeanUtils.instantiateClass(mapperFactoryBeanClass));
		}

		scanner.setSqlSessionTemplateBeanName(annoAttrs.getString("sqlSessionTemplateRef"));
		scanner.setSqlSessionFactoryBeanName(annoAttrs.getString("sqlSessionFactoryRef"));

		List<String> basePackages = new ArrayList<>();
		for (String pkg : annoAttrs.getStringArray("value")) {
			if (StringUtils.hasText(pkg)) {
				if (pkg.startsWith("${") && pkg.endsWith("}")) {
					pkg = environment.getProperty(pkg.substring(2, pkg.length() - 1));
				}
				basePackages.add(pkg);
			}
		}
		for (String pkg : annoAttrs.getStringArray("basePackages")) {
			if (StringUtils.hasText(pkg)) {
				if (pkg.startsWith("${") && pkg.endsWith("}")) {
					pkg = environment.getProperty(pkg.substring(2, pkg.length() - 1));
				}
				basePackages.add(pkg);
			}
		}
		for (Class<?> clazz : annoAttrs.getClassArray("basePackageClasses")) {
			basePackages.add(ClassUtils.getPackageName(clazz));
		}

		scanner.registerFilters();
		scanner.doScan(StringUtils.toStringArray(basePackages));
	}

}
