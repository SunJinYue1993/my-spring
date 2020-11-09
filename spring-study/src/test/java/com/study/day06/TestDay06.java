package com.study.day06;

import com.study.day02.config.HteEntity;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * day06: Spring有几个BeanDefinitionReader, 干什么的？
 */
public class TestDay06 {

	@Test
	public void debug01(){
		ClassPathXmlApplicationContext xml = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("debug01() over...");
	}

	@Test
	public void debug02(){
		AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(HteEntity.class);
		System.out.println("debug02() over...");
	}
}
