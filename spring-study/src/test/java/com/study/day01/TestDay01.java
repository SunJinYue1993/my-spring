package com.study.day01;

import com.study.day01.config.AppConfig;
import com.study.day01.service.OneService;
import com.study.day01.service.TwoService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay01 {

	/**
	 * 情况一: MyBeanFactoryPostProcessor不放入容器
	 * 此时无法获得TwoService, 因为TwoService没有被扫描
	 */
	@Test
	public void test01() {
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//		ac.register(AppConfig.class);
//		ac.refresh();
//		OneService oneService = ac.getBean(OneService.class);
//		Assert.assertNotNull(oneService);
//		TwoService bean = oneService.getTwoService();
//		Assert.assertNull(bean);
	}

	/**
	 * MyBeanFactoryPostProcessor放入容器，此时可以获得TwoService
	 */
	@Test
	public void test02() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();
		TwoService twoService = (TwoService)ac.getBean("oneService");
		Assert.assertEquals("TwoService", "TwoService", twoService.getClassName());
	}
}
