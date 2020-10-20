package com.study.day05;

import com.study.day02.config.HteEntity;
import com.study.day03.AClass;
import com.study.day03.Config03;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDay05 {

	@Test
	public void test01(){
		//AbstractApplicationContext.refresh().obtainFreshBeanFactory()
		// -> AbstractRefreshableApplicationContext.loadBeanDefinitions(beanFactory); 生成BeanDefinitionMap有值
		ClassPathXmlApplicationContext xmlAC = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(xmlAC.getBean("person"));

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(HteEntity.class);// 生成BeanDefinitionMap有值
		ac.refresh();
		System.out.println(ac.getBean(HteEntity.class));
	}
}
