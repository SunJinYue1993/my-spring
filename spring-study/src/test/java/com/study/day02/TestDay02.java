package com.study.day02;

import com.study.day02.config.HteEntity;
import com.study.day02.config.TheConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDay02 {

	/*
	 * @Component要加入容器的方式:
	 * 1. ac.register(@Component修饰的类);直接注册生成AnnotatedGenericBeanDefinition
	 * 2. ac.register(@ComponentScan修饰的类);由ConfigurationClassParser处理
	 * 3. ac.register(@Configuration修饰的类);在这个类上+@ComponentScan也会被ConfigurationClassParser解析到
	 * ConfigurationClassParser主要工作有以下几点:
	  	// TODO：Process any @ComponentScan annotations
		// TODO：Process any @Import annotations
		// TODO：Process any @ImportResource annotations
		// TODO：Process individual @Bean methods
	 */
	@Test
	public void test01() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(TheConfig.class);
//		ac.register(HteEntity.class);
		ac.refresh();
		System.out.println(ac.getBean(HteEntity.class));
	}
}
