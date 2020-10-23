package com.study.day07;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * day07: 从xml配置 -> BeanDefinition的过程？
 * 整体流程 AbstractApplicationContext.refresh().
 */
public class TestDay07 {

	@Test
	public void test01() {
		ClassPathXmlApplicationContext xmlAC = new ClassPathXmlApplicationContext("applicationContext.xml");
		User msb = (User) xmlAC.getBean("msb");
		System.out.println(msb.getEmail());

		System.out.println(xmlAC.getBean(User.class).getEmail());
	}

}
